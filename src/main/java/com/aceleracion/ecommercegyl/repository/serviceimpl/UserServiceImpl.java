package com.aceleracion.ecommercegyl.repository.serviceimpl;

import com.aceleracion.ecommercegyl.dto.mapper.BranchMapper;
import com.aceleracion.ecommercegyl.dto.mapper.RoleMapper;
import com.aceleracion.ecommercegyl.dto.mapper.UserMapper;
import com.aceleracion.ecommercegyl.dto.request.UserFindRequestDTO;
import com.aceleracion.ecommercegyl.dto.request.UserRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.UserResponseDto;
import com.aceleracion.ecommercegyl.exception.MyException;
import com.aceleracion.ecommercegyl.model.*;
import com.aceleracion.ecommercegyl.repository.UserRepository;
import com.aceleracion.ecommercegyl.service.service.AddressService;
import com.aceleracion.ecommercegyl.service.service.BranchService;
import com.aceleracion.ecommercegyl.service.service.RoleService;
import com.aceleracion.ecommercegyl.service.service.UserService;
import com.aceleracion.ecommercegyl.utils.ListValidator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final BranchService branchService;
    private final BranchMapper branchMapper;
    private final RoleMapper roleMapper;
    private final AddressService addressService;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder,
                           RoleService roleService, BranchService branchService, BranchMapper branchMapper,
                           RoleMapper roleMapper, AddressService addressService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
        this.branchService = branchService;
        this.branchMapper = branchMapper;
        this.roleMapper = roleMapper;
        this.addressService = addressService;
    }

    @Transactional
    @Override
    public UserResponseDto createUser(UserRequestDTO userDto) throws MyException {
        if (userDto == null) {
            throw new MyException("entityNull", "userRequest");
        }
        User objUser = userMapper.reqToUser(userDto);
        if (userRepository.findUserByDni(objUser.getDni()) != null) {
            throw new MyException("existDB", "user");
        }
        objUser.setStatus(true);
        objUser.setPassword(passwordEncoder.encode(objUser.getPassword()));
        Role objRole = roleService.findById(userDto.getIdRol());
        objUser.setRole(objRole);
        Branch objBranch = branchService.findById(userDto.getBranchId());
        objUser.setBranch(objBranch);

        Address address = addressService.createAddress(userDto.getStreet(), userDto.getNumber(), userDto.getCityId());
        objUser.setAddress(address);

        userRepository.save(objUser);

        UserResponseDto userResponseDto = userMapper.userToUserResp(objUser);
        userResponseDto.setRoleResponseDTO(roleMapper.roleToResp(objRole));
        userResponseDto.setBranchResponseDTO(branchMapper.branchToResp(objBranch));

        return userResponseDto;
    }

    @Override
    public UserResponseDto findUserByDni(String dni) {
        if (dni.isBlank()) {
            throw new MyException("entityNull", "dni");
        }
        User objUser = userRepository.findUserByDni(dni);
        if (objUser == null) {
            throw new MyException("noExistDB", "user");
        }
        return userMapper.userToUserResp(objUser);

    }

    @Override
    public User findByDni(String dni) {
        if (dni.isBlank()) {
            throw new MyException("entityNull", "dni");
        }

        User objUser = userRepository.findUserByDni(dni);
        if (objUser == null) {
            throw new MyException("noExistDB", "user");
        }

        return objUser;
    }


    @Transactional
    @Override
    public void changeRoleByManager(String dni) {

        if (dni.isBlank()) {
            throw new MyException("entityNull", "dni");
        }

        User existingUser = userRepository.findUserByDni(dni);

        System.out.println(existingUser.getRole()+"------aaaa------");
        if (existingUser != null) {
            Role currentRole = existingUser.getRole();
            System.out.println(currentRole.getRoleName()+" +currentRole.get");
            if (currentRole.getRoleName().equals("seller")) {
                Role role = roleService.findByRoleName("manager");
                System.out.println(role.getRoleName()+" +role.get");
                existingUser.setRole(role);
                System.out.println(existingUser.getRole()+" +existingUser");
                userRepository.save(existingUser);
            } else {
                throw new MyException("noExistDB", "user");
            }
        } else {
            throw new MyException("noExistDB", "user");

        }

    }

    @Transactional
    @Override
    public void changeRolebyCeo(String dni) {
        if (dni.isEmpty()) {
            throw new MyException("entityNull", "dni");
        }

        User existingUser = userRepository.findUserByDni(dni);

        if (existingUser == null) {
            throw new MyException("noExistDB" + "user");
        }

        Role currentRole = existingUser.getRole();
        if (!currentRole.getRoleName().equals("manager")) {
            throw new MyException("noExistDB", "user");
        }

        Role ceoRole = roleService.findByRoleName("ceo");
        existingUser.setRole(ceoRole);
        userRepository.save(existingUser);
    }

    @Override
    public List<UserResponseDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        ListValidator.validateResponseList(users);

        return userMapper.userToUserRespList(users);
    }

    @Override
    public UserResponseDto findUserByName(UserFindRequestDTO userFindRequestDTO) {
        if (userFindRequestDTO == null) {
            throw new MyException("entityNull", "userRequest");
        }
        User objUser = userRepository.findUserByNameAndLastName(userFindRequestDTO.getName(), userFindRequestDTO.getLastName());
        if (objUser == null) {
            throw new MyException("noExistDB", "user");
        }
        return userMapper.userToUserResp(objUser);
    }

    @Override
    public void changeStatus(String dni) {
        if (dni.isEmpty()) {
            throw new MyException("entityNull", "dni");
        }
        User objUser = userRepository.findUserByDni(dni);
        if (objUser == null) {
            throw new MyException("noExistDB", "user");
        }
        boolean status = objUser.getStatus();
        objUser.setStatus(!status);
        userRepository.save(objUser);

    }

    @Transactional
    @Override
    public UserResponseDto updateUser(UserRequestDTO userRequestDTO) {
        if (userRequestDTO == null) {
            throw new MyException("entityNull", "userRequest");
        }

        String dni = userRequestDTO.getDni();
        User existingUser = userRepository.findUserByDni(dni);

        if (existingUser == null) {
            throw new MyException("noExistDB", "user");
        }

        existingUser.setName(userRequestDTO.getName());
        existingUser.setLastName(userRequestDTO.getLastName());
        existingUser.setDni(dni);
        existingUser.setEmail(userRequestDTO.getEmail());
        existingUser.setPhoneNumber(userRequestDTO.getPhoneNumber());
        existingUser.setBirthdate(userRequestDTO.getBirthdate());
        userRepository.save(existingUser);

        return userMapper.userToUserResp(existingUser);
    }

    @Override
    public List<UserResponseDto> findAllByStatus() {
        List<User> users = userRepository.findAllByStatusTrue();
        ListValidator.validateResponseList(users);

        return userMapper.userToUserRespList(users);
    }


    @Override
    public List<UserResponseDto> findAllByBranchAndStatusTrue(Long branchId) {
        List<User> users = userRepository.findAllUserByBranchAndStatusTrue(branchId);
        ListValidator.validateResponseList(users);

        return userMapper.userToUserRespList(users);
    }

    @Override
    public List<UserResponseDto> findAllByBranch(Long branchId) {
        List<User> users = userRepository.findAllUserByBranch(branchId);
        ListValidator.validateResponseList(users);

        return userMapper.userToUserRespList(users);
    }

}

