package tn.esprit.Controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.Entity.Review;
import tn.esprit.Interface.IReviewService;

import java.util.List;

@RestController
@RequestMapping("review")
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class ReviewController {
    final IReviewService reviewService;

    @RequestMapping(method = {RequestMethod.PUT, RequestMethod.POST})
    @ResponseStatus(HttpStatus.CREATED)
    public Review add_review(@RequestBody Review r)
    {

        return reviewService.add_review(r);
    }

    @DeleteMapping("{id}")
    public void delete_review(@PathVariable("id") Long id)
    {
        reviewService.delete_review(id);
    }

    @GetMapping
    public List<Review> getAll_review()
    {

        return reviewService.getAll_review();
    }

    @GetMapping("/{id}")
    public Review getById_review(@PathVariable("id") Long id)
    {
        return reviewService.getById_review(id);
    }
}
