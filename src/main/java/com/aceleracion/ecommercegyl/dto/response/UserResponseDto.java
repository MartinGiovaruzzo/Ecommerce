package com.aceleracion.ecommercegyl.dto.response;

import java.time.LocalDate;

public class UserResponseDto {
    private Long personId;
    private String name;
    private String lastName;
    private String dni;
    private String email;
    private String phoneNumber;
    private LocalDate birthdate;
    private String userName;
    private String password;
    private Boolean status;
    private RoleResponseDTO roleResponseDTO;
    private BranchResponseDTO branchResponseDTO;

    public UserResponseDto() {
    }

    private UserResponseDto(UserRespDTOBuilder builder) {
        this.personId = builder.personId;
        this.name = builder.name;
        this.lastName = builder.lastName;
        this.dni = builder.dni;
        this.email = builder.email;
        this.phoneNumber = builder.phoneNumber;
        this.birthdate = builder.birthdate;
        this.userName = builder.userName;
        this.password = builder.password;
        this.status = true;
        this.roleResponseDTO = builder.roleResponseDTO;
        this.branchResponseDTO = builder.branchResponseDTO;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public RoleResponseDTO getRoleResponseDTO() {
        return roleResponseDTO;
    }

    public void setRoleResponseDTO(RoleResponseDTO roleResponseDTO) {
        this.roleResponseDTO = roleResponseDTO;
    }

    public BranchResponseDTO getBranchResponseDTO() {
        return branchResponseDTO;
    }

    public void setBranchResponseDTO(BranchResponseDTO branchResponseDTO) {
        this.branchResponseDTO = branchResponseDTO;
    }

    public static class UserRespDTOBuilder {
        private Long personId;
        private String name;

        private String lastName;

        private String dni;

        private String email;

        private String phoneNumber;

        private LocalDate birthdate;

        private String userName;

        private String password;

        private Boolean status;

        private RoleResponseDTO roleResponseDTO;

        private BranchResponseDTO branchResponseDTO;

        public UserRespDTOBuilder personId(Long personId) {
            this.personId = personId;
            return this;
        }

        public UserRespDTOBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UserRespDTOBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserRespDTOBuilder dni(String dni) {
            this.dni = dni;
            return this;
        }

        public UserRespDTOBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserRespDTOBuilder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public UserRespDTOBuilder birthdate(LocalDate birthdate) {
            this.birthdate = birthdate;
            return this;
        }

        public UserRespDTOBuilder userName(String userName) {
            this.userName = userName;
            return this;
        }

        public UserRespDTOBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserRespDTOBuilder status(Boolean status) {
            this.status = status;
            return this;
        }

        public UserRespDTOBuilder roleResponseDTO(RoleResponseDTO roleResponseDTO) {
            this.roleResponseDTO = roleResponseDTO;
            return this;
        }

        public UserRespDTOBuilder branchResponseDTO(BranchResponseDTO branchResponseDTO) {
            this.branchResponseDTO = branchResponseDTO;
            return this;
        }

        public UserResponseDto build() {
            return new UserResponseDto(this);
        }

    }

}
