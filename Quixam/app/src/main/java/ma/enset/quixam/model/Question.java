package ma.enset.quixam.model;

import java.util.Date;
import java.util.List;

public class Question {
    private String _id;
    private String content;
    private int score;
    private int duration;
    private Date createdAt;
    private Date updatedAt;
    private String quiz;
    private List<String> answers;

    public Question(String _id, String content, int score, int duration, Date createdAt, Date updatedAt, List<String> answers) {
        this._id = _id;
        this.content = content;
        this.score = score;
        this.duration = duration;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.answers = answers;
    }

    public String get_id() {
        return _id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
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

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }
}
