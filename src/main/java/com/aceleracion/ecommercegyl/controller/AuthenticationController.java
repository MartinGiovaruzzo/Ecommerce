package com.aceleracion.ecommercegyl.controller;


import com.aceleracion.ecommercegyl.dto.request.UserRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.UserResponseDto;
import com.aceleracion.ecommercegyl.dto.response.UserSignInResponseDTO;
import com.aceleracion.ecommercegyl.service.service.AuthenticationService;
import com.aceleracion.ecommercegyl.service.service.UserService;
import com.aceleracion.ecommercegyl.repository.serviceimpl.AuthenticationServiceImpl;
import com.aceleracion.ecommercegyl.repository.serviceimpl.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@CrossOrigin(origins = "http://gyl-ecommerce.s3-website-us-east-1.amazonaws.com/")
@RestController
@RequestMapping("ecommerce/authentication")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    private final UserService userService;

    public AuthenticationController(AuthenticationServiceImpl authenticationService, UserServiceImpl userService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
    }

    @PostMapping("sign-up")
    public ResponseEntity<UserResponseDto> signUp(@Valid @RequestBody UserRequestDTO userRequestDto){
        try{
            UserResponseDto userResponseDto = userService.createUser(userRequestDto);
            return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("sign-in")
    public ResponseEntity<UserSignInResponseDTO> signIn(@RequestBody UserRequestDTO userRequestDTO){

        return new ResponseEntity<>(authenticationService.signInAndReturnJWT(userRequestDTO), HttpStatus.OK);
    }

}