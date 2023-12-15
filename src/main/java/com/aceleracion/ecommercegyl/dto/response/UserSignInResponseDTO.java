package com.aceleracion.ecommercegyl.dto.response;

import com.aceleracion.ecommercegyl.model.Branch;

import javax.persistence.Transient;

public class UserSignInResponseDTO {
    private String userName;
    private String password;
    private String userDni;
    @Transient
    private String token;
    private String rol;
    private Branch branch;

    public UserSignInResponseDTO() {
    }

    public UserSignInResponseDTO(String userName, String password, String userDni, String token, String rol, Branch branch) {
        this.userName = userName;
        this.password = password;
        this.userDni = userDni;
        this.token = token;
        this.rol = rol;
        this.branch = branch;
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

    public String getUserDni() {
        return userDni;
    }

    public void setUserDni(String userDni) {
        this.userDni = userDni;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }
}