package Web.dao;


import Web.models.Book;
import Web.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component // С помощбю аннотации Spring определяет этот класс как кандидата для создания bean.
public class PersonDAO {

    private JdbcTemplate jdbcTemplate; // API по управлению БД

    @Autowired // Автоматически внедряет нужный бин.
    public PersonDAO(JdbcTemplate jdbcTemplate) { // Внедряет бин с данными БД из SpringConfig
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        // RowMapper преображает строки из таблицы Person в объекты класса Person устанавливает значения для полей
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) {
        return  jdbcTemplate.query("SELECT * FROM Person WHERE id = ?", new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person(name, surname, middle_name, birthday) VALUES (?, ?, ?, ?::date)",
                person.getName(), person.getSurname(), person.getmiddle_name(), person.getBirthday());
    }

    public void update(int id, Person updatePerson) {
        jdbcTemplate.update("UPDATE Person SET name=?, surname=?, middle_name=?, birthday=? WHERE id = ?",
                updatePerson.getName(), updatePerson.getSurname(),
                updatePerson.getmiddle_name(), updatePerson.getBirthday(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
    }

    public List<Book> getBooksByPersonId(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE person_id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class));
    }

}
