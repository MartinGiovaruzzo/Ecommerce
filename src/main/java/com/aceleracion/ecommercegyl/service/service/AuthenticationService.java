package com.aceleracion.ecommercegyl.service.service;

import com.aceleracion.ecommercegyl.dto.request.UserRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.UserSignInResponseDTO;

public interface AuthenticationService {
    UserSignInResponseDTO signInAndReturnJWT(UserRequestDTO signInRequest);

}
