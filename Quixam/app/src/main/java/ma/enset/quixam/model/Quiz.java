package ma.enset.quixam.model;

import java.util.Date;
import java.util.List;

public class Quiz {
    private String _id;
    private String title;
    private Date createdAt;
    private Date updatedAt;
    private int __v;
    private String teacher;
    private List<String> students;
    private List<String> questions;

    public Quiz(String _id, String title, Date createdAt, Date updatedAt, int __v, String teacher, List<String> students, List<String> questions) {
        this._id = _id;
        this.title = title;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.__v = __v;
        this.teacher = teacher;
        this.students = students;
        this.questions = questions;
    }

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public List<String> getStudents() {
        return students;
    }

    public void setStudents(List<String> students) {
        this.students = students;
    }

    public List<String> getQuestions() {
        return questions;
    }

    public void setQuestions(List<String> questions) {
        this.questions = questions;
    }
}
