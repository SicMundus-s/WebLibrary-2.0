package Web.servies;

import Web.models.Book;
import Web.models.Person;
import Web.repositories.BooksRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BooksService {

    private final BooksRepositories booksRepositories;

    @Autowired
    public BooksService(BooksRepositories booksRepositories) {
        this.booksRepositories = booksRepositories;
    }

    public List<Book> findAllSortedByYear(boolean sort) {
        if(sort) {
            return booksRepositories.findAll(Sort.by("year"));
        }else
            return booksRepositories.findAll();
    }
    public List<Book> findAll(Integer page, Integer size, boolean sort ) {
        if(sort) {
            return booksRepositories.findAll(PageRequest.of(page, size, Sort.by("year"))).getContent();
        }else
            return booksRepositories.findAll(PageRequest.of(page, size)).getContent();
    }

    public Book findOne(int id) {
        Optional<Book> one = booksRepositories.findById(id);
        return one.orElse(null);
    }


    public Optional<Book> findByTitle(String title) {
        return Optional.ofNullable(booksRepositories.findByTitle(title).stream().findAny().orElse(null));
    }

    @Transactional
    public Book save(Book book) {
        return booksRepositories.save(book);
    }

    @Transactional
    public void update(int id, Book updatedBook) {
        updatedBook.setId(id);
        booksRepositories.save(updatedBook);
    }

    @Transactional
    public void delete(int id) {
        booksRepositories.deleteById(id);
    }

    public Optional<Person> getBooksOwner(int id) {
        return Optional.ofNullable(booksRepositories.findById(id).get().getOwner());
    }

    @Transactional
    public void giveTheBookAway(int id) {
        booksRepositories.findById(id).get().setOwner(null);
    }
    @Transactional
    public void assign(int id, Person person) {
        booksRepositories.findById(id).get().setOwner(person);
    }

    public List<Book> searchByTitle(String request) {
        return booksRepositories.findByTitleStartingWith(request);
    }
}
