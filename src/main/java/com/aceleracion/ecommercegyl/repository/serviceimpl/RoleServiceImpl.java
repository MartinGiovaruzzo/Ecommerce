package com.aceleracion.ecommercegyl.repository.serviceimpl;

import com.aceleracion.ecommercegyl.dto.mapper.RoleMapper;
import com.aceleracion.ecommercegyl.dto.request.RoleRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.RoleResponseDTO;
import com.aceleracion.ecommercegyl.exception.MyException;
import com.aceleracion.ecommercegyl.model.Role;
import com.aceleracion.ecommercegyl.repository.RoleRepository;
import com.aceleracion.ecommercegyl.service.service.RoleService;
import com.aceleracion.ecommercegyl.utils.ListValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public RoleServiceImpl(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @Transactional
    @Override
    public RoleResponseDTO createRole(RoleRequestDTO roleRequestDTO) {
        if (roleRequestDTO == null) {
            throw new MyException("entityNull", "roleRequest");
        }
        if (roleRepository.findByRoleName(roleRequestDTO.getRoleName()) != null) {
            throw new MyException("existDB", "role");

        }
        Role objRole = roleMapper.reqToRole(roleRequestDTO);
        objRole.setStatus(true);
        roleRepository.save(objRole);
        return roleMapper.roleToResp(objRole);
    }


    @Override
    public RoleResponseDTO findRoleById(Long roleId) {
        if (roleId == null) {
            throw new MyException("entityNull", "roleId");
        }
        Optional<Role> objRole = roleRepository.findById(roleId);
        if (objRole.isPresent()) {
            return roleMapper.roleToResp(objRole.get());
        } else {
            throw new MyException("noExistDB", "role");

        }
    }

    @Override
    public Role findById(Long roleId) {
        if (roleId == null) {
            throw new MyException("entityNull", "roleId");
        }
        Optional<Role> objRole = roleRepository.findById(roleId);
        if (objRole.isPresent()) {
            return objRole.get();
        } else {
            throw new MyException("noExistDB", "role");
        }
    }

    @Override
    public List<RoleResponseDTO> findAllRoles() {
        List<Role> roles = roleRepository.findAll();
        ListValidator.validateResponseList(roles);

        return roleMapper.roleToRespList(roles);
    }

    @Override
    public Role findByRoleName(String name) {
        return roleRepository.findByRoleName(name);
    }

    @Override
    @Transactional
    public RoleResponseDTO updateRole(String name, RoleRequestDTO roleRequestDTO) {

        if (roleRequestDTO == null) {
            throw new MyException("entityNull", "roleRequest");
        }
        if (name == null) {
            throw new MyException("entityNull", "name");
        }
        Role objRole = roleRepository.findByRoleName(name);
        if (objRole != null) {
            objRole.setRoleName(roleRequestDTO.getRoleName());
            roleRepository.save(objRole);
            return roleMapper.roleToResp(objRole);
        } else {
            throw new MyException("noExistDB", "role");
        }
    }

}
