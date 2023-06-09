package workshop.chris.security.bookstore.exceptions;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(Long bookId) {
        super("Could not find book with ID: " + bookId);
    }
}
