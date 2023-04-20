package workshop.chris.security.bookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import workshop.chris.security.bookstore.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/my-books")
    public String getMyListOfBooks(Model model, Principal principal) {
        userService.getMyBooks(model, principal);
        return "myBookList";
    }

    @PostMapping("/{userId}/books/{bookId}/add")
    public String addBookToUsersList(@PathVariable("userId") int userId, @PathVariable("bookId") Long bookId, HttpServletRequest request) {
        userService.addBook(userId, bookId);
        String referer = request.getHeader("Referer");
        if (referer != null && referer.contains("/" + bookId)) {
            return "redirect:" + referer;
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/{userId}/books/{bookId}/remove")
    public String removeBookFromUsersList(@PathVariable("userId") int userId, @PathVariable("bookId") Long bookId) {
        userService.removeBook(userId, bookId);
        return "redirect:/my-books";
    }
}
