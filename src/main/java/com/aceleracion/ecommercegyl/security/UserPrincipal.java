package com.aceleracion.ecommercegyl.security;

import com.aceleracion.ecommercegyl.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

public class UserPrincipal implements UserDetails {

    private Long id;
    private String userName;
    private transient String password;
    private transient User user;
    private Set<GrantedAuthority> authorities;

    public UserPrincipal() {
    }

    public UserPrincipal(Long id, String userName, String password, User user, Set<GrantedAuthority> authorities) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.user = user;
        this.authorities = authorities;
    }

    public static UserPrincipalBuilder builder() {
        return new UserPrincipalBuilder();
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
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
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}