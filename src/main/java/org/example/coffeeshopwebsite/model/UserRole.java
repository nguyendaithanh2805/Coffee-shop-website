package org.example.coffeeshopwebsite.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_userRole")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_UserRole_User"))
    private User user;

    @ManyToOne
    @JoinColumn(name = "roleId", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_UserRole_Role"))
    private Role role;

    public UserRole() {
    }

    public UserRole(Long id, User user, Role role) {
        this.id = id;
        this.user = user;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
