package tn.esprit.Service;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.Entity.*;
import tn.esprit.Interface.ITrainingService;
import tn.esprit.Repository.*;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TrainingService implements ITrainingService {
     final QuizRepository quizRepository;
     final TrainingRepository trainingRepository;

     final TraineeRepository traineeRepository;

     final TrainerRepository trainerRepository;

     final SubjectRepository subjectRepository;


     @Override
     public Training add_training(Training t) {
          if(validate_date(t)) {
               t = training_type_check(t);
               return trainingRepository.save(t);
          }
          return null;
     }

     @Override
     public Training add_training_with_image(Training t, MultipartFile image) {
          if(validate_date(t)) {
               t = training_type_check(t);
               try {
                    t.setImage(image_handling(image, t.getTitle()));
                    return trainingRepository.save(t);
               } catch (IOException ioe) {
                    log.error("IO Problem : " + ioe.getMessage());
                    return null;
               }
          }
          return null;

     }

     @Override
     public void delete_training(Long id) {
          Training t = trainingRepository.findById(id).orElse(null);
          if(t.getImage() != null) {
               try {

                    Resource resourceToDelete = new FileSystemResource(t.getImage());
                    File fileToDelete = resourceToDelete.getFile();

                    if (fileToDelete.delete()) {

                         System.out.println("File deleted successfully");
                    } else {
                         log.error("Error deleting file");
                    }


               } catch (IOException ioe) {
                    log.error("Error deleting file");
               }
          }
          trainingRepository.deleteById(id);


     }

     @Override
     public List<Training> getAll_training() {
          return trainingRepository.findAll();
     }

     @Override
     public Training getById_training(Long id) {
          return trainingRepository.findById(id).orElse(null);
     }

     @Override
     public void delete_all() {
          trainingRepository.deleteAll();
     }

     @Override
     @Transactional
     public boolean add_Trainees_To_Training(Long id_trining, List<Long> id_trainee) {
          List<Trainee> trainees = traineeRepository.findAllById(id_trainee);
          Training training = trainingRepository.findById(id_trining).orElse(null);

          trainees.forEach(trainee -> {
               trainee.setTraining(training);
               traineeRepository.save(trainee);
          });
          return true;
     }

     @Override
     @Transactional
     public boolean add_Trainer_To_Training(Long id_trining, Long id_trainer) {
          Training training = trainingRepository.findById(id_trining).orElse(null);
          Trainer trainer = trainerRepository.findById(id_trainer).orElse(null);
          training.setTrainer(trainer);
          trainingRepository.save(training);
          return true;
     }



     @Override
     public List<Training> get_sorted_trainings(String by) {
          switch (by.trim().toLowerCase()) {
               case "date":
                    return trainingRepository.getAllSortedByDate();
               case "duration":
                    return trainingRepository.getAllSortedByDuration();
                case "reviews":
                    return trainingRepository.getAllSortedByReviews();
               case "ratings":
                    return sortByReviews();

          }

          return trainingRepository.findAll();
     }

     @Override
     public Training get_By_Title_training(String title) {
          return trainingRepository.findByTitle(title).orElse(null);
     }

     @Override
     @Transactional
     public Training affect_quizes_to_training(List<Long> ids,String training_title) {
          Training t =  trainingRepository.findByTitle(training_title).orElse(null);
          Set<Quiz> quizSet = new HashSet<>();
          if(t != null) {
               for (Long id : ids)
               {
                    Quiz quiz = quizRepository.findById(id).orElse(null);
                    if(quiz != null)
                         quizSet.add(quiz);
               }
               t.setQuizes(quizSet);
               trainingRepository.save(t);
               return t;
          }
          return null;
     }




     private List<Training> sortByReviews()
     {

          List<Training> trainingsorted = trainingRepository
                  .findAll().stream()
                  .sorted(Comparator.comparingInt(trainig -> trainig.getReviews()
                          .stream().mapToInt(review -> review.getRating())
                          .sum()))
                  .collect(Collectors.toList());
          Collections.reverse(trainingsorted);
          return trainingsorted;
     }

     @Override
     @Transactional
     public Training add_training_with_quizes(Training training, Set<Quiz> quizes,MultipartFile image) {
          if(validate_date(training)) {
               try {
                    training = training_type_check(training);
                    log.info("===========>"+quizes.toString());
                    filter_quizes(quizes);
                    log.info("---------->"+quizes.toString());
                    if (quizes.size() > 0) {
                         log.info("++++++++++++++++>"+quizes.toString());
                         training.setQuizes(quizes);

                    }
                    training.setImage(image_handling(image, training.getTitle()));
                    log.info("*********************>"+training.getQuizes().toString());
                    return trainingRepository.save(training);
               } catch (IOException ioe) {
                    log.error("IO Problem : " + ioe.getMessage());
                    return null;
               }
          }
          else
               return null;
     }

     @Override
     public List<Training> getAvailable() {
          LocalDate localDate = LocalDate.now();
          Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
          return trainingRepository.findByStartdateAfter(date);
     }


     private void filter_quizes(Set<Quiz> quizzes)
     {
          for(Quiz quiz : quizzes)
          {
               if(quizRepository.checkIfQuestionExists(quiz.getQuestion().trim()))
                    quizzes.remove(quiz);

          }
     }

     private String image_handling(MultipartFile image,String title) throws IOException {
          String destination ="src\\main\\resources\\" + title + "\\"+title+".png";
          //t.setImage( "src\\main\\resources\\" + t.getTitle() + "\\"+t.getTitle()+".png");

          InputStream inputStream = image.getInputStream();

          // Create a byte array to hold the content of the uploaded file
          byte[] bytes = FileCopyUtils.copyToByteArray(inputStream);

          // Create a new file using the destination path
          File destinationFile = new File(destination);


          // Write the bytes to the new file
          FileUtils.writeByteArrayToFile(destinationFile, bytes);
          return destination;
     }

     private Training training_type_check(Training training)
     {


          if(subjectRepository.retrieveByTitle(training.getTitle().trim().toLowerCase()).orElse(null) == null)
               training.setType_t(Type_T.external);
          else
               training.setType_t(Type_T.internal);

          return training;
     }


     @Scheduled(fixedDelay = 3000)
     public void delete_unused()
     {
          traineeRepository.findAll().forEach(trainee -> {
               if(trainee.getTraining() == null)
                    traineeRepository.deleteById(trainee.getId());
          });
          trainerRepository.findAll().forEach(trainer -> {
               if(trainer.getTrainings() == null || trainer.getTrainings().size() ==0)
                    trainerRepository.deleteById(trainer.getId());
          });
     }

     private Boolean validate_date(Training training)
     {
          if(training.getEnddate().before(training.getStartdate()))
               return false;
          return true;

     }

     @Scheduled(fixedDelay = 50000)
     public void update_training_type()
     {
          for(Training training :trainingRepository.findAll())
          {
               Boolean check = false;
               for(Subject subject : subjectRepository.findAll())
               {

                    if(training.getTitle().trim().equalsIgnoreCase(subject.getTitle().trim()))
                    {
                         check = true;
                    }

               }

               if(check)
                    training.setType_t(Type_T.internal);
               else
                    training.setType_t(Type_T.external);
               trainingRepository.save(training);
          }
     }


}
