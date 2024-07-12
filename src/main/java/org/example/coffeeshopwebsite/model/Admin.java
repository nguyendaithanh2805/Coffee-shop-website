package org.example.coffeeshopwebsite.model;

public class Admin {
    private int userId;
    private String fullName;

    public Admin() {
    }

    public Admin(int userId, String fullName) {
        this.userId = userId;
        this.fullName = fullName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
