package org.example.coffeeshopwebsite.model;

public class Article {
    private int articleId;
    private int userId;
    private String name;
    private String image;
    private String description;
    private Boolean status;
    private User user;

    public Article() {
    }

    public Article(int articleId, int userId, String name, String image, String description, Boolean status, User user) {
        this.articleId = articleId;
        this.userId = userId;
        this.name = name;
        this.image = image;
        this.description = description;
        this.status = status;
        this.user = user;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
