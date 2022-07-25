package Web.repositories;

import Web.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BooksRepositories extends JpaRepository<Book, Integer> {

    Optional<Book> findByTitle(String title);

}
