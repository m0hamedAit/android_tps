package ma.enset.tprestapi.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserGithub implements Serializable {
    private String login;
    @SerializedName("avatar_url")      // on ajout @SerializeName si le champ n'a pas meme nom que dans json
    private String avatarUrl;
    private String name;
    private String location;
    private String email;
    private int followers;
    private int following;
    private int twitter_username;
    private String url;
    private String type;
    private double score;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public int getFollowing() {
        return following;
    }

    public void setFollowing(int following) {
        this.following = following;
    }

    public int getTwitter_username() {
        return twitter_username;
    }

    public void setTwitter_username(int twitter_username) {
        this.twitter_username = twitter_username;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
