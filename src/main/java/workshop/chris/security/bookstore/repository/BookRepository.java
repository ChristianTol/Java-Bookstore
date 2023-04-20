package workshop.chris.security.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import workshop.chris.security.bookstore.entity.Book;

import java.util.List;
import java.util.Optional;

import static org.hibernate.loader.Loader.SELECT;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b WHERE b.title LIKE %:keyword% OR b.author LIKE %:keyword%")
    List<Book> findByKeyword(@Param("keyword") String keyword);

}
