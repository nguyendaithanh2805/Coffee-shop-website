package org.example.coffeeshopwebsite.model;

public class Customer {
    private int userId;
    private String fullName;

    public Customer() {
    }

    public Customer(int userId, String fullName) {
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
