package com.TechHunt.USER_SERVICE.models;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
public class CustomUserDetail implements UserDetails {

    private final String email;
    private final String password;
    private final String username; // Custom field
    private final Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetail(String email, String password,
                            String username, Collection<? extends GrantedAuthority> authorities){

        this.email = email;
        this.password = password;
        this.username = username;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

}
