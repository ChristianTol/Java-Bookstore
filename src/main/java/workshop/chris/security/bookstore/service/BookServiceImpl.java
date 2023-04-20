package workshop.chris.security.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import workshop.chris.security.bookstore.entity.Book;
import workshop.chris.security.bookstore.entity.Review;
import workshop.chris.security.bookstore.entity.User;
import workshop.chris.security.bookstore.repository.BookRepository;
import workshop.chris.security.bookstore.repository.ReviewRepository;
import workshop.chris.security.bookstore.repository.UserRepository;

import javax.persistence.EntityNotFoundException;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.*;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, UserRepository userRepository, ReviewRepository reviewRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.reviewRepository = reviewRepository;
    }

    public void getAllBooks(String keyword, Model model, Principal principal) {
        String email = principal.getName();
        User user = userRepository.findByEmail(email);
        List<Book> books;

        if (keyword != null) {
            books = bookRepository.findByKeyword(keyword);
        } else {
            books = bookRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        }

        Map<Long, Boolean> addedBooks = new HashMap<>();

        if (user != null) {
            Set<Book> bookLists = user.getBookList();
            for (Book bookList : bookLists) {
                addedBooks.put(bookList.getId(), true);
            }
        }

        model.addAttribute("books", books);
        model.addAttribute("user", user);
        model.addAttribute("addedBooks", addedBooks);
    }

    public void getBookPage(Long bookId, Model model, Principal principal) {
        String email = principal.getName();
        User user = userRepository.findByEmail(email);
        Book book = bookRepository.findById(bookId).orElseThrow(EntityNotFoundException::new);

        List<Book> allBooks = bookRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));

        Map<Long, Boolean> addedBooks = new HashMap<>();

        if (user != null) {
            Set<Book> bookLists = user.getBookList();
            for (Book bookList : bookLists) {
                addedBooks.put(bookList.getId(), true);
            }
        }

        List<Review> reviews = reviewRepository.findByBook(book);

        long positiveReviewsCount = reviews.stream().filter(r -> r.getRating() >= 3).count();
        long negativeReviewsCount = reviews.size() - positiveReviewsCount;
        double positivePercentage = ((double) positiveReviewsCount / reviews.size()) * 100;
        double negativePercentage = ((double) negativeReviewsCount / reviews.size()) * 100;

        positivePercentage = Math.round(positivePercentage * 10) / 10.0;
        negativePercentage = Math.round(negativePercentage * 10) / 10.0;

        model.addAttribute("positivePercentage", positivePercentage);
        model.addAttribute("negativePercentage", negativePercentage);
        model.addAttribute("book", book);
        model.addAttribute("reviews", reviews);
        model.addAttribute("user", user);
        model.addAttribute("addedBooks", addedBooks);
    }

    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    public Book getBookById(Long id) {
        Optional<Book> result = bookRepository.findById(id);
        return result.orElse(null);
    }

    @Transactional
    public void deleteBook(Long bookId, Model model, Principal principal) {
        Book book = bookRepository.findById(bookId).orElseThrow(EntityNotFoundException::new);
        String email = principal.getName();
        User user = userRepository.findByEmail(email);

        if(user.getBookList() != null) {
            user.getBookList().remove(book);
        }
        userRepository.save(user);
        bookRepository.delete(book);
        model.addAttribute("bookList", user.getBookList());
    }
}

