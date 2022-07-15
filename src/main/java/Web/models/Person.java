package Web.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Person {
    private int id;
    @NotEmpty
    @Size(min = 2, max = 45, message = "Name should be between 2 and 45 characters")
    private String name;
    @NotEmpty
    @Size(min = 2, max = 45, message = "Name should be between 2 and 45 characters")
    private String surname;
    @NotEmpty
    @Size(min = 2, max = 45, message = "Name should be between 2 and 45 characters")
    private String middle_name;
    @NotEmpty
    private String birthday;

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
}
