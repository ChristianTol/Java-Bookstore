package workshop.chris.security.bookstore.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.*;

@Builder
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private String price;
    private String image;

    @ManyToMany(mappedBy = "bookList")
    private Set<User> users = new HashSet<>();

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private Set<Review> reviews = new HashSet<>();

    public Book() {
    }

    public Book(String title, String author, String price, String image) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.image = image;
    }

    public Book(Long id, String title, String author, String price, String image) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
        this.image = image;
    }

    public Book(String title, String author, String price, String image, Set<User> users) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.image = image;
        this.users = users;
    }

    public Book(Long id, String title, String author, String price, String image, Set<User> users) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
        this.image = image;
        this.users = users;
    }

    public Book(String title, String author, String price, String image, Set<User> users, Set<Review> reviews) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.image = image;
        this.users = users;
        this.reviews = reviews;
    }

    public Book(Long id, String title, String author, String price, String image, Set<User> users, Set<Review> reviews) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
        this.image = image;
        this.users = users;
        this.reviews = reviews;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", price='" + price + '\'' +
                ", image='" + image + '\'' +
                ", users=" + users +
                ", reviews=" + reviews +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
