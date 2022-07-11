package Web.models;

public class Book {
    private int id;
    private String title;
    private String author;
    private String _year;
    private int personid;

    public Book() {

    };

    public Book(int id, String title, String author, String _year, int personid) {
        this.id = id;
        this.title = title;
        this.author = author;
        this._year = _year;
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

    public String get_year() {
        return _year;
    }

    public void set_year(String _year) {
        this._year = _year;
    }

    public int getPersonid() {
        return personid;
    }

    public void setPersonid(int personid) {
        this.personid = personid;
    }
}
