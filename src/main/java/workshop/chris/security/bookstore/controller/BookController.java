package workshop.chris.security.bookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import workshop.chris.security.bookstore.entity.Book;
import workshop.chris.security.bookstore.repository.UserRepository;
import workshop.chris.security.bookstore.service.BookService;
import workshop.chris.security.bookstore.service.BookServiceImpl;
import workshop.chris.security.bookstore.service.ReviewService;
import workshop.chris.security.bookstore.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
@RequestMapping("/")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/add")
    public String addNewBook() {
        return "addBook";
    }

    @GetMapping("/{bookId}/details")
    public String getBookDetails(@PathVariable("bookId") Long bookId, Model model, Principal principal) {
        bookService.getBookPage(bookId, model, principal);
        return "detailsBook";
    }

    @GetMapping
    public String getAllBooks(String keyword, Model model, Principal principal) {
        bookService.getAllBooks(keyword, model, principal);
        return "bookList";
    }

    @PostMapping("/save")
    public String addOrUpdateBook(@ModelAttribute Book book) {
        bookService.saveBook(book);
        return "redirect:/";
    }

    @PostMapping("/{bookId}/edit")
    public String editBook(@PathVariable("bookId") Long bookId, Model model) {
        Book book = bookService.getBookById(bookId);
        model.addAttribute("book", book);
        return "editBook";
    }

    @PostMapping("/{bookId}/delete")
    public String deleteBook(@PathVariable Long bookId, Model model, Principal principal) {
        bookService.deleteBook(bookId, model, principal);
        return "redirect:/";
    }
}
