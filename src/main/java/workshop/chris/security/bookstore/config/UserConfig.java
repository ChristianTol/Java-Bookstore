package workshop.chris.security.bookstore.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import workshop.chris.security.bookstore.entity.Role;
import workshop.chris.security.bookstore.entity.User;
import workshop.chris.security.bookstore.repository.UserRepository;

@Configuration
public class UserConfig {
    private final PasswordEncoder passwordEncoder;

    public UserConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    CommandLineRunner commandLineRunner(UserRepository repository) {
        return args -> {
            var admin = User.builder()
                    .firstName("Christian")
                    .lastName("Tol")
                    .email("admin@gmail.com")
                    .password(passwordEncoder.encode("password"))
                    .role(Role.ROLE_ADMIN)
                    .build();
            repository.save(admin);

            var user = User.builder()
                    .firstName("Chris")
                    .lastName("Tol")
                    .email("user@gmail.com")
                    .password(passwordEncoder.encode("password"))
                    .role(Role.ROLE_USER)
                    .build();
            repository.save(user);
        };
    }
}
