package Web.models;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty
    @Size(min = 2, max = 45, message = "Name should be between 2 and 45 characters")
    @Column(name = "name")
    private String name;
    @NotEmpty
    @Size(min = 2, max = 60, message = "surname should be between 2 and 60 characters")
    @Column(name = "surname")
    private String surname;
    @NotEmpty
    @Size(min = 2, max = 60, message = "middle_name should be between 2 and 60 characters")
    @Column(name = "middle_name")
    private String middle_name;
    @NotEmpty
    @Column(name = "birthday")
    private String birthday;

    @OneToMany(mappedBy = "owner")
    private List<Book> bookList;

     public Person() {

     }
    public Person(int id, String name, String surname, String middle_name, String birthday) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.middle_name = middle_name;
        this.birthday = birthday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getmiddle_name() {
        return middle_name;
    }

    public void setmiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", middle_name='" + middle_name + '\'' +
                ", birthday='" + birthday + '\'' +
                '}';
    }
}
