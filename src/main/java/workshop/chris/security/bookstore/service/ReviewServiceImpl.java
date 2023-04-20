package workshop.chris.security.bookstore.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import workshop.chris.security.bookstore.entity.Book;
import workshop.chris.security.bookstore.entity.Review;
import workshop.chris.security.bookstore.entity.User;
import workshop.chris.security.bookstore.exceptions.ResourceNotFoundException;
import workshop.chris.security.bookstore.repository.BookRepository;
import workshop.chris.security.bookstore.repository.ReviewRepository;
import workshop.chris.security.bookstore.repository.UserRepository;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository, UserRepository userRepository, BookRepository bookRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    public Review getReviewById(Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Review with id %d not found", id)));
    }

    @Override
    public void addOrUpdateReview(Review review, Long bookId, Principal principal) {
        String email = principal.getName();
        Optional<Book> book = bookRepository.findById(bookId);
        if (book.isPresent()) {
            Optional<User> user = Optional.ofNullable(userRepository.findByEmail(email));
            if (user.isPresent()) {
                review.setBook(book.get());
                review.setUser(user.get());
                reviewRepository.save(review);
            }
        }
    }

    @Override
    public void deleteReview(Long reviewId, Long bookId, Principal principal) {
        reviewRepository.deleteById(reviewId);
    }
}
