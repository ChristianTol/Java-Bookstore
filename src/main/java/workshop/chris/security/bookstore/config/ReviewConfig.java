package workshop.chris.security.bookstore.config;

import com.github.javafaker.Faker;
import org.apache.commons.io.FileUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import workshop.chris.security.bookstore.entity.Book;
import workshop.chris.security.bookstore.entity.Review;
import workshop.chris.security.bookstore.entity.User;
import workshop.chris.security.bookstore.repository.BookRepository;
import workshop.chris.security.bookstore.repository.ReviewRepository;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.stream.IntStream;

@Configuration
public class ReviewConfig implements CommandLineRunner {
    private final ReviewRepository reviewRepository;
    private final Faker faker = new Faker();

    public ReviewConfig(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public void run(String... args) throws Exception {
//        List<Review> reviews = IntStream.range(0, 20)
//                .mapToObj(i -> {
//                    Review review = Review.builder()
//                            .title(faker.harryPotter().book())
//                            .comment(faker.harryPotter().quote())
//                            .rating((long) faker.number().numberBetween(1, 5))
//                            .user(User.builder().id(faker.number().numberBetween(1, 2)).build())
//                            .book(Book.builder().id((long) faker.number().numberBetween(1, 20)).build())
//                            .build();
//                    return review;
//                }).toList();
//
//        reviewRepository.saveAll(reviews);
    }
}
