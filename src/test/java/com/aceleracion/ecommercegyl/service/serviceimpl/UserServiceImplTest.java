package com.aceleracion.ecommercegyl.service.serviceimpl;

import com.aceleracion.ecommercegyl.dto.mapper.BranchMapper;
import com.aceleracion.ecommercegyl.dto.mapper.RoleMapper;
import com.aceleracion.ecommercegyl.dto.mapper.UserMapper;
import com.aceleracion.ecommercegyl.dto.request.UserRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.BranchResponseDTO;
import com.aceleracion.ecommercegyl.dto.response.RoleResponseDTO;
import com.aceleracion.ecommercegyl.dto.response.UserResponseDto;
import com.aceleracion.ecommercegyl.exception.MyException;
import com.aceleracion.ecommercegyl.model.*;
import com.aceleracion.ecommercegyl.repository.UserRepository;
import com.aceleracion.ecommercegyl.repository.serviceimpl.UserServiceImpl;
import com.aceleracion.ecommercegyl.service.service.AddressService;
import com.aceleracion.ecommercegyl.service.service.BranchService;
import com.aceleracion.ecommercegyl.service.service.RoleService;
import com.aceleracion.ecommercegyl.service.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {


    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private RoleService roleService;

    @Mock
    private BranchService branchService;

    @Mock
    private BranchMapper branchMapper;

    @Mock
    private RoleMapper roleMapper;

    @Mock
    private AddressService addressService;

    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserServiceImpl(userRepository, userMapper, passwordEncoder, roleService, branchService,
                branchMapper, roleMapper, addressService);
    }

    @Test
    public void testCreateUser_Success() throws MyException {
        UserRequestDTO userRequestDTO = new UserRequestDTO();
        userRequestDTO.setName("Maria");
        userRequestDTO.setLastName("Perez");
        userRequestDTO.setDni("1234567");
        userRequestDTO.setEmail("mperez@example.com");
        userRequestDTO.setPhoneNumber("1234567890");
        userRequestDTO.setBirthdate(LocalDate.of(1990, 1, 1));
        userRequestDTO.setUserName("mperez");
        userRequestDTO.setPassword("Password123!");
        userRequestDTO.setIdRol(1L);
        userRequestDTO.setBranchId(1L);
        userRequestDTO.setStreet("Espora");
        userRequestDTO.setNumber("123");
        userRequestDTO.setCityId(1L);

        User objUser = createObject.createUserTest();
        Role objRole = createObject.createRoleTest();
        Branch objBranch = createObject.createBranchTest();
        Address address = createObject.createAddressTest();
        UserResponseDto expectedResponse = new UserResponseDto();

        when(userMapper.reqToUser(userRequestDTO)).thenReturn(objUser);
        when(userRepository.findUserByDni(objUser.getDni())).thenReturn(null);
        when(passwordEncoder.encode(objUser.getPassword())).thenReturn("encodedPassword");
        when(roleService.findById(userRequestDTO.getIdRol())).thenReturn(objRole);
        when(branchService.findById(userRequestDTO.getBranchId())).thenReturn(objBranch);
        when(addressService.createAddress(userRequestDTO.getStreet(), userRequestDTO.getNumber(), userRequestDTO.getCityId())).thenReturn(address);
        when(userMapper.userToUserResp(objUser)).thenReturn(expectedResponse);
        when(roleMapper.roleToResp(objRole)).thenReturn(new RoleResponseDTO());
        when(branchMapper.branchToResp(objBranch)).thenReturn(new BranchResponseDTO());

        UserResponseDto actualResponse = userService.createUser(userRequestDTO);
        expectedResponse.setPersonId(1L);
        expectedResponse.setName("Maria");
        expectedResponse.setLastName("Perez");
        expectedResponse.setDni("1234567");
        expectedResponse.setEmail("mperez@example.com");
        expectedResponse.setPhoneNumber("1234567890");
        expectedResponse.setBirthdate(LocalDate.of(1990, 1, 1));
        expectedResponse.setUserName("mperez");
        expectedResponse.setPassword("Password123!");
        expectedResponse.setStatus(true);

        assertNotNull(actualResponse);
        assertEquals(expectedResponse, actualResponse);
    }


    @Test
    public void testCreateUser_NullUserRequestDTO_ThrowsMyException() {
        try {
            userService.createUser(null);
            fail("Expected MyException to be thrown");
        } catch (MyException e) {
            assertNotNull(e);
        }
    }

    @Test
    public void testCreateUser_UserExists_ThrowsMyException() {

        UserRequestDTO userRequestDTO = new UserRequestDTO();
        User objUser = new User();

        when(userMapper.reqToUser(userRequestDTO)).thenReturn(objUser);
        when(userRepository.findUserByDni(objUser.getDni())).thenReturn(new User());

        try {
            userService.createUser(userRequestDTO);
            fail("Expected MyException to be thrown");
        } catch (MyException e) {
            assertNotNull(e);
        }
    }

    @Test
    public void testFindAllUsers() {
        List<User> users = new ArrayList<>();
        User user1 = createObject.createUserTest();
        User user2 = createObject.createUserTest();
        users.add(user1);
        users.add(user2);

        List<UserResponseDto> userResponseDtos = new ArrayList<>();
        UserResponseDto responseDto1 = new UserResponseDto();
        responseDto1.setPersonId(1L);
        responseDto1.setName("Maria");
        responseDto1.setLastName("Perez");
        responseDto1.setDni("45645646");
        responseDto1.setEmail("mperez@gmail.com");
        responseDto1.setPhoneNumber("1234567895");
        responseDto1.setBirthdate(LocalDate.parse("2002-06-01"));
        responseDto1.setUserName("mperez");
        responseDto1.setPassword("Password123!");
        responseDto1.setStatus(true);
        userResponseDtos.add(responseDto1);
        UserResponseDto responseDto2 = new UserResponseDto();
        responseDto2.setPersonId(1L);
        responseDto2.setName("Pedro");
        responseDto2.setLastName("Gimenez");
        responseDto2.setDni("456454546");
        responseDto2.setEmail("pgimenez@gmail.com");
        responseDto2.setPhoneNumber("1234567895");
        responseDto2.setBirthdate(LocalDate.parse("2002-06-01"));
        responseDto2.setUserName("pgimenez");
        responseDto2.setPassword("Password123&");
        responseDto2.setStatus(true);
        userResponseDtos.add(responseDto2);

        when(userRepository.findAll()).thenReturn(users);
        when(userMapper.userToUserRespList(users)).thenReturn(userResponseDtos);

        List<UserResponseDto> result = userService.findAllUsers();

        assertEquals(userResponseDtos, result);
    }

    @Test
    public void testUpdateUser_Success() throws MyException {

        UserRequestDTO userRequestDTO = new UserRequestDTO();
        userRequestDTO.setName("Maria");
        userRequestDTO.setLastName("Perez");
        userRequestDTO.setDni("1234567");
        userRequestDTO.setEmail("mperez@example.com");
        userRequestDTO.setPhoneNumber("1234567890");
        userRequestDTO.setBirthdate(LocalDate.of(1990, 1, 1));

        User existingUser = new User();
        existingUser.setDni("1234567");

        UserResponseDto expectedResponse = new UserResponseDto();
        expectedResponse.setPersonId(1L);
        expectedResponse.setName("Maria");
        expectedResponse.setLastName("Perez");
        expectedResponse.setDni("1234567");
        expectedResponse.setEmail("mperez@example.com");
        expectedResponse.setPhoneNumber("1234567890");
        expectedResponse.setBirthdate(LocalDate.of(1990, 1, 1));

        when(userRepository.findUserByDni(userRequestDTO.getDni())).thenReturn(existingUser);
        when(userRepository.save(existingUser)).thenReturn(existingUser);

        when(userMapper.userToUserResp(existingUser)).thenReturn(expectedResponse);

        UserResponseDto actualResponse = userService.updateUser(userRequestDTO);

        assertNotNull(actualResponse);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void testChangeStatus_Success() throws MyException {

        String dni = "1234567";

        User objUser = new User();
        objUser.setDni(dni);
        objUser.setStatus(true);

        when(userRepository.findUserByDni(dni)).thenReturn(objUser);
        when(userRepository.save(objUser)).thenReturn(objUser);

        userService.changeStatus(dni);

        assertFalse(objUser.getStatus());
    }


}