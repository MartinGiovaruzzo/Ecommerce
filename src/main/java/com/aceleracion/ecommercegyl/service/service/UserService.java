package com.aceleracion.ecommercegyl.service.service;

import com.aceleracion.ecommercegyl.dto.request.UserFindRequestDTO;
import com.aceleracion.ecommercegyl.dto.request.UserRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.UserResponseDto;
import com.aceleracion.ecommercegyl.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {
    UserResponseDto createUser(UserRequestDTO userDto) ;

    UserResponseDto findUserByDni(String dni);

    User findByDni(String dni);

    @Transactional
    void changeRoleByManager(String dni);

    @Transactional
    void changeRolebyCeo(String dni);

    List<UserResponseDto> findAllUsers();

    UserResponseDto findUserByName(UserFindRequestDTO userFindRequestDTO);

    void changeStatus(String dni);

    UserResponseDto updateUser(UserRequestDTO userRequestDTO);

    List<UserResponseDto> findAllByStatus();

    List<UserResponseDto> findAllByBranchAndStatusTrue(Long branchId);

    List<UserResponseDto> findAllByBranch(Long branchId);
}

