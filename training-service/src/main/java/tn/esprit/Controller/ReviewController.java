package tn.esprit.Controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.Dto.ReviewDto;
import tn.esprit.Entity.Review;
import tn.esprit.Interface.IReviewService;
import tn.esprit.Interface.ITrainingService;
import tn.esprit.Mapper.ReviewMapper;

import javax.ws.rs.QueryParam;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("review")
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class ReviewController {
    final IReviewService reviewService;

    final ITrainingService trainingService;

    @RequestMapping(method = {RequestMethod.PUT, RequestMethod.POST})
    @ResponseStatus(HttpStatus.CREATED)
    public ReviewDto add_review(@RequestBody ReviewDto r)
    {

        return ReviewMapper
                .mapToDto(reviewService
                        .add_review(ReviewMapper
                                .mapToEntity(r,trainingService)));
    }

    @DeleteMapping("{id}")
    public void delete_review(@PathVariable("id") Long id)
    {
        reviewService.delete_review(id);
    }

    @GetMapping
    public List<ReviewDto> getAll_review()
    {
        List<ReviewDto> reviewDtos = new ArrayList<>();
        reviewService.getAll_review().forEach(review -> reviewDtos.add(ReviewMapper.mapToDto(review)));
        return reviewDtos;
    }

    @DeleteMapping("all")
    public void delete_all()
    {
        reviewService.delete_all();
    }
}
