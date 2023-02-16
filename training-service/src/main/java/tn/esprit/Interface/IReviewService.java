package tn.esprit.Interface;

import tn.esprit.Entity.Review;

import java.util.List;

public interface IReviewService {
    Review add_review(Review r);
    void delete_review(Long id);
    List<Review> getAll_review();
    Review getById_review(Long id);
}
