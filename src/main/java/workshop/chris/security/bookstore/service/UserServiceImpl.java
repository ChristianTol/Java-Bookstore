package workshop.chris.security.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import workshop.chris.security.bookstore.entity.Book;
import workshop.chris.security.bookstore.entity.User;
import workshop.chris.security.bookstore.repository.BookRepository;
import workshop.chris.security.bookstore.repository.UserRepository;

import java.security.Principal;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BookRepository bookRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void getMyBooks(Model model, Principal principal) {
        String email = principal.getName();
        User user = userRepository.findByEmail(email);
        Set<Book> myBooks = user.getBookList();
        model.addAttribute("myBooks", myBooks);
        model.addAttribute("user", user);
    }

    @Override
    public void addBook(int userId, Long bookId) {
        User user = userRepository.findById(userId).orElseThrow();
        Book book = bookRepository.findById(bookId).orElseThrow();
        user.getBookList().add(book);
        userRepository.save(user);
    }

    @Override
    public void removeBook(int userId, Long bookId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new IllegalArgumentException("Invalid book ID"));

        // Make sure the book is associated with the user
        if (!user.getBookList().contains(book)) {
            throw new IllegalArgumentException("Book is not associated with user");
        }

        // Remove the book from the user's book list
        user.getBookList().remove(book);

        // Remove the user from the book's user list
        book.getUsers().remove(user);

        userRepository.save(user);
        bookRepository.save(book);
    }

}
