package tn.esprit.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import tn.esprit.Entity.Review;
import tn.esprit.Entity.Training;
import tn.esprit.Interface.IReviewService;
import tn.esprit.Repository.ReviewRepository;
import tn.esprit.Repository.TrainingRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReviewService implements IReviewService {
    final ReviewRepository reviewRepository;
    final TrainingRepository trainingRepository;

    @Override
    public Review add_review(Long training_id,Review r) {
        Training t = trainingRepository.findById(training_id).orElse(null);
        r.setTraining(t);
        return reviewRepository.save(r);
    }

    @Override
    public void delete_review(Long id) {
        reviewRepository.deleteById(id);
    }

    @Override
    public List<Review> getAll_review() {
        return reviewRepository.findAll();
    }

}
