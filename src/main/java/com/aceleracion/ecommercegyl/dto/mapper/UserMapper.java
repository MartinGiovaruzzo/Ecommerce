package com.aceleracion.ecommercegyl.dto.mapper;

import com.aceleracion.ecommercegyl.dto.request.UserRequestDTO;
import com.aceleracion.ecommercegyl.dto.request.UserSignInRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.UserResponseDto;
import com.aceleracion.ecommercegyl.dto.response.UserSignInResponseDTO;
import com.aceleracion.ecommercegyl.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring",
        uses = {RoleMapper.class, BranchMapper.class})
@Component
public interface UserMapper {
    User reqToUser(UserRequestDTO userRequestDTO);

    @Mapping(source = "role", target = "roleResponseDTO")
    @Mapping(source = "branch", target = "branchResponseDTO")
    UserResponseDto userToUserResp(User user);

    UserResponseDto userToUserResp(Optional<User> objUser);

    UserSignInResponseDTO userToUSerSignInRespDTO(User user);

    UserSignInRequestDTO userSignInReqToUser(User user);

    List<UserResponseDto> userToUserRespList(List<User> users);
}
