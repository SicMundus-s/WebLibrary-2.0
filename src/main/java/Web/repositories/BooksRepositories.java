package Web.repositories;

import Web.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BooksRepositories extends JpaRepository<Book, Integer> {

    Optional<Book> findByTitle(String title);
    List<Book> findByTitleStartingWith(String title);

}
