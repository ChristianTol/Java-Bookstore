package workshop.chris.security.bookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.TemplateMode;

@SpringBootApplication
public class SecurityBookStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityBookStoreApplication.class, args);
	}


}
