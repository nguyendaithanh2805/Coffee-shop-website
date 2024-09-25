package org.example.coffeeshopwebsite.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomUserDetails implements UserDetails {
    private User user;
    private Collection<? extends GrantedAuthority> grantedAuthorities; // Collection chua authorities cua nguoi dung

    public CustomUserDetails() {
    }

    public CustomUserDetails(User user, Collection<? extends GrantedAuthority> grantedAuthorities) {
        this.user = user;
        this.grantedAuthorities = grantedAuthorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }
}
