package com.aceleracion.ecommercegyl.security;


import com.aceleracion.ecommercegyl.model.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

public class UserPrincipalBuilder {
    private Long id;
    private String userName;
    private String password;
    private User user;
    private Set<GrantedAuthority> authorities;

    UserPrincipalBuilder() {
    }

    public UserPrincipalBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public UserPrincipalBuilder userName(String userName) {
        this.userName = userName;
        return this;
    }

    public UserPrincipalBuilder password(String password) {
        this.password = password;
        return this;
    }

    public UserPrincipalBuilder user(User user) {
        this.user = user;
        return this;
    }

    public UserPrincipalBuilder authorities(Set<GrantedAuthority> authorities) {
        this.authorities = authorities;
        return this;
    }

    public UserPrincipal build() {
        return new UserPrincipal(id, userName, password, user, authorities);
    }
}
