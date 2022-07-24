package Web.models;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty
    @Size(min = 1, max = 100, message = "Name should be between 2 and 100 characters")
    @Column(name = "title")
    private String title;
    @NotEmpty
    @Size(min = 2, max = 150, message = "Name should be between 2 and 150 characters")
    @Column(name = "author")
    private String author;
    @NotEmpty
    @Column(name = "year_book")
    private String year_book;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "ic")
    private Person owner;
    public Book() {

    };

    public Book(int id, String title, String author, String year_book) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year_book = year_book;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getYear_book() {
        return year_book;
    }

    public void setYear_book(String year_book) {
        this.year_book = year_book;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year_book='" + year_book + '\'' +
                '}';
    }
}
