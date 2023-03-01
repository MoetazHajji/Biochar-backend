package tn.esprit.Interface;

import org.springframework.web.multipart.MultipartFile;
import tn.esprit.Entity.Training;

import java.util.List;

public interface ITrainingService {
    Training add_training(Training t);
    Training add_training_with_image(Training t, MultipartFile image);
    void delete_training(Long id);
    List<Training> getAll_training();
    Training getById_training(Long id);

    void delete_all();

    boolean add_Trainees_To_Training(Long id_trining,List<Long> id_trainee);

    boolean add_Trainer_To_Training(Long id_trining,Long id_trainer);

    List<Training> generate_trainings();

    List<Training> get_sorted_trainings(int by);

    Training get_By_Title_training(String title);

   Training affect_quizes_to_training(List<Long> ids,String training_title);

}
