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
        jdbcTemplate.update("INSERT INTO book(title, author, year_book) VALUES (?, ?, ?::date)",
                book.getTitle(), book.getAuthor(), book.getYear_book());
    }

    public Optional<Person> getBooksOwner(int id) {
        Optional<Person> my  = jdbcTemplate.query("SELECT * FROM Book JOIN Person ON book.person_id = person.id WHERE book.id = ?", new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
        System.out.println(my);
        return my;
    }

    public void giveTheBookAway(int id) {
        jdbcTemplate.update("UPDATE book SET person_id = NULL WHERE id = ?", id);
    }

    public void assign(int id, Person selectedPerson) {
        jdbcTemplate.update("UPDATE book SET person_id = ? WHERE id = ?", selectedPerson.getId(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM book WHERE id = ?", id);
    }
}
