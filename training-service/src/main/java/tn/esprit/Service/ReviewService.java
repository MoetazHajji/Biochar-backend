package tn.esprit.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import tn.esprit.Entity.Review;
import tn.esprit.Interface.IReviewService;
import tn.esprit.Repository.ReviewRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReviewService implements IReviewService {
    final ReviewRepository reviewRepository;

    @Override
    public Review add_review(Review r) {
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

    @Override
    public Review getById_review(Long id) {
        return reviewRepository.findById(id).orElse(null);
    }
}
