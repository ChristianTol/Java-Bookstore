package workshop.chris.security.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import workshop.chris.security.bookstore.entity.Book;
import workshop.chris.security.bookstore.entity.Review;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByBook(Book book);
}
