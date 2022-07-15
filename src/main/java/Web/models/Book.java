package Web.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Book {
    private int id;
    @NotEmpty
    @Size(min = 1, max = 150, message = "Name should be between 2 and 150 characters")
    private String title;
    @NotEmpty
    @Size(min = 2, max = 150, message = "Name should be between 2 and 150 characters")
    private String author;
    @NotEmpty
    private String year_book;

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

}
