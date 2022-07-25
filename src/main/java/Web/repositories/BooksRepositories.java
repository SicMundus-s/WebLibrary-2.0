package Web.repositories;

import Web.models.Book;
import Web.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BooksRepositories extends JpaRepository<Book, Integer> {

    Optional<Book> findByTitle(String title);

}
