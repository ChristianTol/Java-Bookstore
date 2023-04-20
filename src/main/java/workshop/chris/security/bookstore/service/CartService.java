package workshop.chris.security.bookstore.service;

import org.springframework.stereotype.Service;
import workshop.chris.security.bookstore.entity.Cart;

@Service
public interface CartService {
    Cart addToCart(int userId, Long bookId);

    Cart getCartByUserId(int userId);
}
