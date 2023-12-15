package com.aceleracion.ecommercegyl.repository.serviceimpl;

import com.aceleracion.ecommercegyl.dto.request.UserRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.UserSignInResponseDTO;
import com.aceleracion.ecommercegyl.model.User;
import com.aceleracion.ecommercegyl.security.UserPrincipal;
import com.aceleracion.ecommercegyl.security.jwt.JwtProvider;
import com.aceleracion.ecommercegyl.security.jwt.JwtProviderImpl;
import com.aceleracion.ecommercegyl.service.service.AuthenticationService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;


@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final JwtProvider jwtProvider;

    private final AuthenticationManager authenticationManager;


    public AuthenticationServiceImpl(JwtProviderImpl jwtProvider, AuthenticationManager authenticationManager) {
        this.jwtProvider = jwtProvider;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public UserSignInResponseDTO signInAndReturnJWT(UserRequestDTO signInRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getUserName(), signInRequest.getPassword()));
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        String jwt = jwtProvider.generateToken(userPrincipal);
        User signInUser = userPrincipal.getUser();
        signInUser.setToken(jwt);
        UserSignInResponseDTO userResp = new UserSignInResponseDTO();
        userResp.setUserName(signInUser.getUserName());
        userResp.setPassword(signInUser.getPassword());
        userResp.setToken(jwt);
        userResp.setBranch(signInUser.getBranch());
        userResp.setUserDni(signInUser.getDni());
        userResp.setRol(signInUser.getRole().getRoleName());

        return userResp;
    }

}
