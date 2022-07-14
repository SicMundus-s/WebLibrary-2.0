package Web.dao;


import Web.models.Book;
import Web.models.Person;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE id = ?", new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }

     public void save(Book book) {
        jdbcTemplate.update("INSERT INTO book(title, author, yearbook) VALUES (?, ?, ?)",
                book.getTitle(), book.getAuthor(), book.getYearbook());
    }

    public Optional<Person> getBooksOwner(int id) {
        Optional<Person> my  = jdbcTemplate.query("SELECT * FROM Book JOIN Person ON book.personid = person.id WHERE book.id = ?", new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
        System.out.println(my);
        return my;
    }
}
