package ma.enset.quixam.model;

import java.util.Date;

public class Answers {
    private String _id;
    private String content;
    private boolean isTrue;
    private Date createdAt;
    private Date updatedAt;
    private String question;



    public Answers(String _id, String content, boolean isTrue, Date createdAt, Date updatedAt) {
        this._id = _id;
        this.content = content;
        this.isTrue = isTrue;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public boolean isTrue() {
        return isTrue;
    }

    public void setTrue(boolean aTrue) {
        isTrue = aTrue;
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

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
