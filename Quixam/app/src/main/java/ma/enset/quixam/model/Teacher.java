package ma.enset.quixam.model;

import java.util.Date;
import java.util.List;

public class Teacher {
    private String _id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Date createdAt;
    private Date updatedAt;
    private List<String> quizes;

    public Teacher(String _id, String firstname, String lastname, String email, String password, Date createdAt, Date updatedAt, List<String> quizes) {
        this._id = _id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.quizes = quizes;
    }

    public String get_id() {
        return _id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<String> getQuizes() {
        return quizes;
    }

    public void setQuizes(List<String> quizes) {
        this.quizes = quizes;
    }
}
