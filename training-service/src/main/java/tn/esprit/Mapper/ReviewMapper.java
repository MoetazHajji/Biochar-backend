package tn.esprit.Mapper;


import tn.esprit.Dto.ReviewDto;
import tn.esprit.Entity.Review;
import tn.esprit.Interface.ITrainingService;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class ReviewMapper {
    public static Review mapToEntity(ReviewDto reviewDto, ITrainingService trainingService){
        LocalDate localDate = LocalDate.now();
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        Review quiz = Review.builder()
                .id(reviewDto.getId_review())
                .comment(reviewDto.getReview())
                .rating(reviewDto.getRating())
                .date_r(date)
                .user(reviewDto.getUser_name())
                .training(trainingService.get_By_Title_training(reviewDto.getTraining_title()))
                .build();
        return quiz;
    }

    public static ReviewDto mapToDto(Review review){
        String title = "";
        if(review.getTraining() != null)
            title = review.getTraining().getTitle();

        ReviewDto reviewDto = ReviewDto.builder()
                .id_review(review.getId())
                .rating(review.getRating())
                .review(review.getComment())
                .date(review.getDate_r())
                .user_name(review.getUser())
                .training_title(title)
                .build();
        return reviewDto;
    }
}
