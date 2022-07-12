package Web.models;

public class Book {
    private int id;
    private String title;
    private String author;
    private String _year_;
    private int personid;

    public Book() {

    };

    public Book(int id, String title, String author, String _year_, int personid) {
        this.id = id;
        this.title = title;
        this.author = author;
        this._year_ = _year_;
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

    public String get_year_() {
        return _year_;
    }

    public void set_year_(String _year_) {
        this._year_ = _year_;
    }

    public int getPersonid() {
        return personid;
    }

    public void setPersonid(int personid) {
        this.personid = personid;
    }
}
