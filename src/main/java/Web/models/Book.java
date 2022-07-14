package Web.models;

public class Book {
    private int id;
    private String title;
    private String author;
    private String yearbook;

    public Book() {

    };

    public Book(int id, String title, String author, String yearbook, int personid) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.yearbook = yearbook;
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

    public String getYearbook() {
        return yearbook;
    }

    public void setyearbook(String yearbook) {
        this.yearbook = yearbook;
    }

}
