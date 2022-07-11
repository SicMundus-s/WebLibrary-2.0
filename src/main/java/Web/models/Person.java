package Web.models;

public class Person {
    private int id;
    private String _name;
    private String surname;
    private String middle_name;
    private String birthday;

     public Person() {

     }
    public Person(int id, String _name, String surname, String middle_name, String birthday) {
        this.id = id;
        this._name = _name;
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
        return _name;
    }

    public void setName(String _name) {
        this._name = _name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
