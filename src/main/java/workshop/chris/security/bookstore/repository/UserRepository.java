package workshop.chris.security.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import workshop.chris.security.bookstore.entity.Book;
import workshop.chris.security.bookstore.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    Optional<User> findByEmailOptional(String email);

    @Query("SELECT u FROM User u WHERE u.email = :email")
    User findByEmail(String email);
}
