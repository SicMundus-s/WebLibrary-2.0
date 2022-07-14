package Web.models;

public class Book {
    private int id;
    private String title;
    private String author;
    private String year_book;

    public Book() {

    };

    public Book(int id, String title, String author, String year_book, int personid) {
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
