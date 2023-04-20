package workshop.chris.security.bookstore.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import workshop.chris.security.bookstore.entity.Review;

import java.security.Principal;
import java.util.List;

@Service
public interface ReviewService {
    Review getReviewById(Long id);
    void addOrUpdateReview(Review review, Long bookId, Principal principal);
    void deleteReview(Long reviewId, Long bookId, Principal principal);
}
