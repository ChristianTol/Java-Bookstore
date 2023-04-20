package workshop.chris.security.bookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import workshop.chris.security.bookstore.entity.Book;
import workshop.chris.security.bookstore.entity.Review;
import workshop.chris.security.bookstore.service.ReviewService;

import java.security.Principal;

@Controller
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/{bookId}/save")
    public String addOrUpdateReview(@PathVariable("bookId") Long bookId, Long reviewId,  @ModelAttribute Review review , Principal principal) {
        reviewService.addOrUpdateReview(review, bookId, principal);
        return "redirect:/{bookId}/details";
    }

    @PostMapping("/{bookId}/review/{reviewId}/edit")
    public String editBook(@PathVariable("bookId") Long bookId, @PathVariable("reviewId") Long reviewId, Model model) {
        Review review = reviewService.getReviewById(reviewId);
        model.addAttribute("editReview", review);
        model.addAttribute("bookId", bookId);
        return "detailsBook";
    }

    @PostMapping("/{bookId}/review/{reviewId}/delete")
    public String deleteReview(@PathVariable("bookId") Long bookId, @PathVariable("reviewId") Long reviewId, Principal principal) {
        reviewService.deleteReview(reviewId, bookId, principal);
        return "redirect:/{bookId}/details";
    }
}
