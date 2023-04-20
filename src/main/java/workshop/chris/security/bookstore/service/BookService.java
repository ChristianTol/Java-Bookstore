package workshop.chris.security.bookstore.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import workshop.chris.security.bookstore.entity.Book;

import java.security.Principal;

@Service
public interface BookService {
    void getAllBooks(String keyword, Model model, Principal principal);
    void getBookPage(Long bookId, Model model, Principal principal);
    Book getBookById(Long id);
    void saveBook(Book book);
    void deleteBook(Long bookId, Model model, Principal principal);
}
