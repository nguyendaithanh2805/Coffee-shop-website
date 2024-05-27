package org.example.coffeeshopwebsite.service;

import org.example.coffeeshopwebsite.model.User;
import org.example.coffeeshopwebsite.model.UserDetailsImpl;
import org.example.coffeeshopwebsite.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        if(user == null)
            throw new UsernameNotFoundException("username not found");
        Collection<GrantedAuthority> grantedAuthorities = new HashSet<>();
        Set<UserRole> roles = user.getUserRoles();
        for (UserRole userRole : roles) {
            grantedAuthorities.add(new SimpleGrantedAuthority(userRole.getRole().getName()));
        }
        return new UserDetailsImpl(user, grantedAuthorities);
    }
}
