package com.aceleracion.ecommercegyl.dto.mapper;

import com.aceleracion.ecommercegyl.dto.request.RoleRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.RoleResponseDTO;
import com.aceleracion.ecommercegyl.model.Role;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface RoleMapper {
    Role  reqToRole(RoleRequestDTO rolRequestDTO);
    RoleResponseDTO roleToResp(Role role);

    List<RoleResponseDTO> roleToRespList(List<Role> roles);
}
