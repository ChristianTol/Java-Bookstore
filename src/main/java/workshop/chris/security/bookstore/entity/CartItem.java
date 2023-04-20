package workshop.chris.security.bookstore.entity;

import javax.persistence.*;

@Entity
@Table(name = "cart_items")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    private int quantity;

    public CartItem() {
    }

    public CartItem(Cart cart, Book book, int quantity) {
        this.cart = cart;
        this.book = book;
        this.quantity = quantity;
    }

    public CartItem(Book book, int quantity) {
        this.book = book;
        this.quantity = quantity;
    }

    public CartItem(Book book) {
        this.book = book;
        this.quantity = 1;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // constructors, getters, and setters
}
