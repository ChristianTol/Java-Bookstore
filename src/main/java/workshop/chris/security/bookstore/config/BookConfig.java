package workshop.chris.security.bookstore.config;

import com.github.javafaker.Faker;
import org.apache.commons.io.FileUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import workshop.chris.security.bookstore.entity.Book;
import workshop.chris.security.bookstore.repository.BookRepository;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.IntStream;

@Component
public class BookConfig implements CommandLineRunner {

    private final BookRepository bookRepository;
    private final Faker faker = new Faker();

    public BookConfig(BookRepository bookRepository) { this.bookRepository = bookRepository; }

    public String price() {
        return price(0, 100);
    }

    public String price(double min, double max) {
        double price =  min + (faker.random().nextDouble() * (max - min));
        return new DecimalFormat("#0.00").format(price);
    }

        @Override
        public void run(String... args) throws Exception {

            List<Book> books = IntStream.range(0, 20)
                    .mapToObj(i -> {
                        Book book = Book.builder()
                                .title(faker.book().title())
                                .author(faker.book().author())
                                .price(faker.commerce().price())
                                .image("https://picsum.photos/200/300?random=" + i)
                                .build();
                        try {
                            URL imageUrl = new URL(book.getImage());

                            File imageDir = new File("/path/to/images/");
                            if (!imageDir.exists()) {
                                imageDir.mkdir();
                            }

                            File imageFile = new File("/path/to/images/" + book.getId() + ".jpg");
                            if (!imageFile.exists()) {
                                imageFile.createNewFile();
                            }

                            FileUtils.copyURLToFile(imageUrl, imageFile);
                            book.setImage(imageFile.getAbsolutePath());
                        } catch (IOException e) {
                            // handle exception
                        }
                        return book;
                    }).toList();

            bookRepository.saveAll(books);

        }
}
