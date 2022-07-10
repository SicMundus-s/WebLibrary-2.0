package Web.dao;


import Web.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component // С помощбю аннотации Spring определяет этот класс как кандидата для создания bean.
public class PersonDAO {

    private JdbcTemplate jdbcTemplate; // API по управлению БД

    @Autowired // Автоматически внедряет нужный бин.
    public PersonDAO(JdbcTemplate jdbcTemplate) { // Внедряет бин с данными БД из SpringConfig
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        // RowMapper преображает строки из таблицы Person в объекты класса Person устанавливает значения для полей
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<Person>());
    }

    public Person show(int id) {
        return  jdbcTemplate.query("SELECT * FROM Person WHERE id = ?", new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }
}
