package workshop.chris.security.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import workshop.chris.security.bookstore.entity.Cart;
import workshop.chris.security.bookstore.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }
    @PostMapping("/{userId}/books/{bookId}")
    public Cart addToCart(@PathVariable int userId, @PathVariable Long bookId) {
        return cartService.addToCart(userId, bookId);
    }

    @PostMapping("/{userId}/books/{bookId}/remove")
    public Cart removeFromCart(@PathVariable int userId, @PathVariable Long bookId) {
        return cartService.removeFromCart(userId, bookId);
    }

    @GetMapping("/{userId}")
    public Cart getCart(@PathVariable int userId) {
        return cartService.getCartByUserId(userId);
    }
}
