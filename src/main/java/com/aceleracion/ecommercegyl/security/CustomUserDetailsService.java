package com.aceleracion.ecommercegyl.security;

import com.aceleracion.ecommercegyl.model.User;
import com.aceleracion.ecommercegyl.repository.UserRepository;
import com.aceleracion.ecommercegyl.utils.SecurityUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Set;


@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findUserByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario no fue encontrado: " + username));

        Set<GrantedAuthority> authorities = Set.of(SecurityUtils.convertToAuthority(user.getRole().getRoleName()));

        return UserPrincipal.builder()
                .user(user)
                .id(user.getPersonId())
                .userName(username)
                .password(user.getPassword())
                .authorities(authorities)
                .build();
    }
}
