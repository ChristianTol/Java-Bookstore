package workshop.chris.security.bookstore.entity;

import workshop.chris.security.bookstore.entity.Book;
import workshop.chris.security.bookstore.entity.CartItem;
import workshop.chris.security.bookstore.entity.User;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems = new ArrayList<>();

    // constructors, getters, and setters

    public void addCartItem(Book book, int quantity) {
        for (CartItem item : cartItems) {
            if (item.getBook().getId().equals(book.getId())) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        cartItems.add(new CartItem(this, book, quantity));
    }

    public void removeItem(Book book) {
        cartItems.removeIf(item -> item.getBook().getId().equals(book.getId()));
    }

    public void clear() {
        cartItems.clear();
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void removeCartItem(Long cartItemId) {
        cartItems.removeIf(item -> item.getId().equals(cartItemId));
    }

    public int getNumberOfItems() {
        return cartItems.stream()
                .mapToInt(CartItem::getQuantity)
                .sum();
    }

//    public BigDecimal getTotalPrice() {
//        return cartItems.stream()
//                .map(item -> item.getBook().getPrice().multiply(new BigDecimal(item.getQuantity())))
//                .reduce(BigDecimal.ZERO, BigDecimal::add);
//    }
}
