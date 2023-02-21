package tn.esprit.Service;

import ai.djl.MalformedModelException;
import ai.djl.Model;
import ai.djl.inference.Predictor;
import ai.djl.modality.Classifications;
import ai.djl.ndarray.NDArray;
import ai.djl.ndarray.NDManager;
import ai.djl.repository.zoo.Criteria;
import ai.djl.repository.zoo.ModelNotFoundException;
import ai.djl.repository.zoo.ModelZoo;
import ai.djl.translate.TranslateException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.Entity.Trainee;
import tn.esprit.Entity.Trainer;
import tn.esprit.Entity.Training;
import tn.esprit.Interface.ITrainingService;
import tn.esprit.Repository.TraineeRepository;
import tn.esprit.Repository.TrainerRepository;
import tn.esprit.Repository.TrainingRepository;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TrainingService implements ITrainingService {
    final TrainingRepository trainingRepository;

    final TraineeRepository traineeRepository;

    final TrainerRepository trainerRepository;

    @Override
    public Training add_training(Training t) {
        return trainingRepository.save(t);
    }

    @Override
    public Training add_training_with_image(Training t, MultipartFile image) {
        try {
            String destination = "C:\\Users\\SBS\\Pictures\\Feedback\\tests\\" + t.getTitle() + ".png";

            InputStream inputStream = image.getInputStream();

            // Create a byte array to hold the content of the uploaded file
            byte[] bytes = FileCopyUtils.copyToByteArray(inputStream);

            // Create a new file using the destination path
            File destinationFile = new File(destination);


            // Write the bytes to the new file
            FileUtils.writeByteArrayToFile(destinationFile, bytes);
            t.setImage(destination);
             return trainingRepository.save(t);
        } catch (IOException ioe) {
            log.error("IO Problem : " + ioe.getMessage());
            return null;
        }

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
    public boolean add_Trainer_To_Training(Long id_trining, Long id_trainer) {
        Training training = trainingRepository.findById(id_trining).orElse(null);
        Trainer trainer = trainerRepository.findById(id_trainer).orElse(null);
        training.setTrainer(trainer);
        trainingRepository.save(training);
        return true;
    }

    @Override
    public List<Training> generate_trainings()  {
      /*  try {
            Criteria<String, Classifications> criteria = Criteria.builder()
                    .setTypes(String.class, Classifications.class)
                    .build();

            Model model = ModelZoo.loadModel(criteria);
            NDManager manager = NDManager.newBaseManager();
            String text = "This is a positive sentence.";
            NDArray input = manager.create(text);
            Predictor<String, Float[]> predictor = model.newPredictor(new String[] {"input"});
            Float[] output = predictor.predict(String.valueOf(input)).singletonOrThrow();
            return null;
        }
        catch (ModelNotFoundException mnfe)
        {
            log.error("Model not found : " + mnfe.getMessage());
        }
        catch (MalformedModelException mme)
        {
            log.error("Malformed model : " + mme.getMessage());
        }
        catch (IOException ioe)
        {
            log.error("IO error : " + ioe.getMessage());
        } catch (TranslateException te) {
            log.error("Translate error : " + te.getMessage());
        }*/
        return null;

    }

    @Override
    public List<Training> get_sorted_trainings(int by) {
        switch (by) {
            case 0:
                return trainingRepository.getAllSortedByDate();
            case 1:
                return trainingRepository.getAllSortedByDuration();
            case 2:
                return trainingRepository.getAllSortedByReviews();
            case 3:
                return sortByReviews();
        }

        return trainingRepository.findAll();
    }


    private List<Training> sortByReviews()
    {
/*
        return trainingRepository
                .findAll().stream()
                .sorted(Comparator.comparingInt(training -> training.getReviews()
                .stream()
                .mapToInt(training::getRating)
                .sum())
               .reversed())
               .collect(Collectors.toList());*/
        List<Training> trainingsorted = trainingRepository
                .findAll().stream()
                .sorted(Comparator.comparingInt(trainig -> trainig.getReviews()
                        .stream().mapToInt(review -> review.getRating())
                        .sum()))
                .collect(Collectors.toList());
        Collections.reverse(trainingsorted);
        return trainingsorted;
    }
}
