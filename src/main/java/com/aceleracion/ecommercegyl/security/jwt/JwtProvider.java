package com.aceleracion.ecommercegyl.security.jwt;

import com.aceleracion.ecommercegyl.model.User;
import com.aceleracion.ecommercegyl.security.UserPrincipal;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

public interface JwtProvider {

    String generateToken(UserPrincipal auth);

    Authentication getAuthentication(HttpServletRequest request);

    String generateToken (User user);

    boolean isTokenValid (HttpServletRequest request);
}
