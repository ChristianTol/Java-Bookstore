package workshop.chris.security.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import workshop.chris.security.bookstore.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
}