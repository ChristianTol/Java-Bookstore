package workshop.chris.security.bookstore.service;

import org.springframework.stereotype.Service;
import workshop.chris.security.bookstore.entity.Book;
import workshop.chris.security.bookstore.entity.Cart;
import workshop.chris.security.bookstore.entity.CartItem;
import workshop.chris.security.bookstore.entity.User;
import workshop.chris.security.bookstore.repository.BookRepository;
import workshop.chris.security.bookstore.repository.CartRepository;
import workshop.chris.security.bookstore.repository.UserRepository;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public CartServiceImpl(CartRepository cartRepository, UserRepository userRepository, BookRepository bookRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }
    @Override
    public Cart addToCart(int userId, Long bookId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
        CartItem cartItem = new CartItem(book);
        Cart cart = user.getCart();
        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
            user.setCart(cart);
        }
        cart.addCartItem(book, cartItem.getQuantity());
        return cartRepository.save(cart);
    }

    @Override
    public Cart getCartByUserId(int userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Cart cart = user.getCart();
        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
            user.setCart(cart);
        }
        return cart;
    }
}
