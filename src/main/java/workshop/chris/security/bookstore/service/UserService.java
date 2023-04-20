package workshop.chris.security.bookstore.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.security.Principal;

@Service
public interface UserService {
    void getMyBooks(Model model, Principal principal);
    void addBook(int userId, Long bookId);
    void removeBook(int userId, Long bookId);
}
