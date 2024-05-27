package org.example.coffeeshopwebsite.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "tbl_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String gender;
    private Boolean isEnabled;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<UserRole> userRoles;

    public User() {
    }

    public User(Long id, String username, String password, String gender, Boolean isEnabled, Set<UserRole> userRoles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.isEnabled = isEnabled;
        this.userRoles = userRoles;
    }

    public User(Long id, String username, String password, String gender, Boolean isEnabled) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.isEnabled = isEnabled;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Boolean getEnabled() {
        return isEnabled;
    }

    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }
}
