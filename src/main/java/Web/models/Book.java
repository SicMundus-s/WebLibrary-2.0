package Web.models;

public class Book {
    private int id;
    private String title;
    private String author;
    private String year;
    private int personid;

    public Book() {

    };

    public Book(int id, String title, String author, String year, int personid) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.personid = personid;
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

    public String getyear() {
        return year;
    }

    public void setyear(String year) {
        this.year = year;
    }

    public int getPersonid() {
        return personid;
    }

    public void setPersonid(int personid) {
        this.personid = personid;
    }
}
