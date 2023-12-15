package com.aceleracion.ecommercegyl.service.serviceimpl;

import com.aceleracion.ecommercegyl.dto.request.RoleRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.ProvinceResponseDTO;
import com.aceleracion.ecommercegyl.dto.response.RoleResponseDTO;
import com.aceleracion.ecommercegyl.exception.MyException;
import com.aceleracion.ecommercegyl.model.Province;
import com.aceleracion.ecommercegyl.model.Role;
import com.aceleracion.ecommercegyl.repository.RoleRepository;

import com.aceleracion.ecommercegyl.dto.mapper.RoleMapper;
import com.aceleracion.ecommercegyl.repository.serviceimpl.RoleServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RoleServiceImplTest {

    @Mock
    private RoleRepository roleRepository;
    @Mock
    private RoleMapper roleMapper;
    @InjectMocks
    private RoleServiceImpl roleService;

    private Role role;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        roleService = new RoleServiceImpl(roleRepository, roleMapper);
    }

    @Test
    void createRoleWithValidRequestReturnsRoleResponseDTO() {

        try {

            RoleRequestDTO roleRequestDTO = new RoleRequestDTO();
            roleRequestDTO.setRoleName("admin");

            Role role = new Role();
            role.setRoleName("admin");

            RoleResponseDTO expectedResponse = new RoleResponseDTO();
            expectedResponse.setRoleName("admin");

            when(roleRepository.findByRoleName("admin")).thenReturn(null);
            when(roleMapper.reqToRole(roleRequestDTO)).thenReturn(role);
            when(roleMapper.roleToResp(role)).thenReturn(expectedResponse);

            RoleResponseDTO actualResponse = roleService.createRole(roleRequestDTO);

            assertNotNull(actualResponse);
            assertEquals(expectedResponse, actualResponse);
            verify(roleRepository, times(1)).findByRoleName("admin");
            verify(roleMapper, times(1)).reqToRole(roleRequestDTO);
            verify(roleRepository, times(1)).save(role);
            verify(roleMapper, times(1)).roleToResp(role);
        } catch (MyException ex) {
            ex.getMessage();
        }
    }


    @Test
    public void createRoleWithInvalidRoleNameShouldThrowMyException() {

        when(roleRepository.findByRoleName(any(String.class))).thenReturn(new Role());

        String nombreRolCorto = "ad";
        RoleRequestDTO rolRequestDTO = new RoleRequestDTO();
        rolRequestDTO.setRoleName(nombreRolCorto);

        assertThrows(MyException.class, () -> roleService.createRole(rolRequestDTO));

        verify(roleRepository, times(1)).findByRoleName(any(String.class));

        verify(roleRepository, never()).save(any(Role.class));

    }

    @Test
    public void findRoleResponseByIdIfRoleExists() {

        Long roleId = 1L;
        Role role = new Role();
        RoleResponseDTO expectedResponse = new RoleResponseDTO();

        when(roleRepository.findById(roleId)).thenReturn(Optional.of(role));
        when(roleMapper.roleToResp(role)).thenReturn(expectedResponse);

        try {
            RoleResponseDTO actualResponse = roleService.findRoleById(roleId);
            assertEquals(expectedResponse, actualResponse);

        } catch (MyException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Test
    public void findRoleResponseByIdIfRoleNotExists() {

        Long roleId = 2L;
        when(roleRepository.findById(roleId)).thenReturn(Optional.empty());

        try {
            roleService.findRoleById(roleId);
            fail("MyException debería haber sido lanzada");

        } catch (MyException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Test
    void findRoleByIdSuccess() {

        Long roleId = 1L;
        Role expectedRole = new Role();

        when(roleRepository.findById(roleId)).thenReturn(Optional.of(expectedRole));

        Role actualRole = roleService.findById(roleId);

        assertEquals(expectedRole, actualRole);

    }

    @Test
    public void findRoleByIdShouldThrowsMyException() {

        Long roleId = 2L;
        when(roleRepository.findById(roleId)).thenReturn(Optional.empty());

        MyException exception = assertThrows(MyException.class, () -> roleService.findById(roleId));

        assertEquals("El recurso: role no existe en la base de datos", exception.getMessage());

    }


    @Test
    void findAllRole_ReturnsRoleResponseDTOList() {
        List<Role> roles = new ArrayList<>();

        Role role1 = new Role();
        role1.setRoleName("admin");
        roles.add(role1);

        Role role2 = new Role();
        role2.setRoleName("user");
        roles.add(role2);

        when(roleRepository.findAll()).thenReturn(roles);

        List<RoleResponseDTO> expectedResponse = new ArrayList<>();

        RoleResponseDTO roleResponseDTO = roleMapper.roleToResp(role1);
        expectedResponse.add(roleResponseDTO);

        RoleResponseDTO roleResponseDTO2 = roleMapper.roleToResp(role2);
        expectedResponse.add(roleResponseDTO2);
        when(roleMapper.roleToRespList(roles)).thenReturn(expectedResponse);

        List<RoleResponseDTO> actualResponse = roleService.findAllRoles();

        assertNotNull(actualResponse);
        assertEquals(2, actualResponse.size());

        verify(roleRepository, times(1)).findAll();

        for (Role role : roles) {
            verify(roleMapper, times(1)).roleToResp(role);
        }
    }

    @Test
    void updateRole_ExistingRole_ReturnsUpdatedRoleResponseDTO() {
        String existingRoleName = "Existing Role";
        RoleRequestDTO roleRequestDTO = new RoleRequestDTO();
        roleRequestDTO.setRoleName("Updated Role");

        Role existingRole = new Role();
        existingRole.setRoleName(existingRoleName);

        RoleMapper roleMapper = Mockito.mock(RoleMapper.class);
        RoleRepository roleRepository = Mockito.mock(RoleRepository.class);

        RoleServiceImpl roleService = new RoleServiceImpl(roleRepository, roleMapper);

        RoleResponseDTO expectedResponseDTO = new RoleResponseDTO();
        when(roleMapper.roleToResp(existingRole)).thenReturn(expectedResponseDTO);

        when(roleRepository.findByRoleName(existingRoleName)).thenReturn(existingRole);

        RoleResponseDTO result = roleService.updateRole(existingRoleName, roleRequestDTO);

        assertNotNull(result);
        assertEquals(expectedResponseDTO, result);
    }

}
