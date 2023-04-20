package workshop.chris.security.bookstore.entity;

import lombok.Builder;

import javax.persistence.*;

@Entity
@Builder
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @Column(columnDefinition="TEXT")
    private String comment;
    private Long rating;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Review() {
    }

    public Review(String title, String comment, Long rating, Book book, User user) {
        this.title = title;
        this.comment = comment;
        this.rating = rating;
        this.book = book;
        this.user = user;
    }

    public Review(Long id, String title, String comment, Long rating, Book book, User user) {
        this.id = id;
        this.title = title;
        this.comment = comment;
        this.rating = rating;
        this.book = book;
        this.user = user;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", comment='" + comment + '\'' +
                ", rating=" + rating +
                ", book=" + book +
                ", user=" + user.getId() +
                '}';
    }
}
