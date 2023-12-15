package com.aceleracion.ecommercegyl.service.service;

import com.aceleracion.ecommercegyl.dto.request.RoleRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.RoleResponseDTO;
import com.aceleracion.ecommercegyl.model.Role;
import java.util.List;

public interface RoleService {

    RoleResponseDTO createRole(RoleRequestDTO rolRequestDTO);

    RoleResponseDTO findRoleById(Long id);

    Role findById(Long id);

    List<RoleResponseDTO> findAllRoles();

    Role findByRoleName(String name);

    RoleResponseDTO updateRole(String name, RoleRequestDTO roleRequestDTO);
}
