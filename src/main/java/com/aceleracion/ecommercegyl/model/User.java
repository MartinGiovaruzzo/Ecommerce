package com.aceleracion.ecommercegyl.model;


import javax.persistence.*;
import java.time.LocalDate;


@Entity
public class User  extends Person{
    private String userName;
    private String password;
    @ManyToOne()
    @JoinColumn(name = "roleId")
    private Role role;
    @ManyToOne()
    @JoinColumn(name = "branchId")
    private Branch branch;
    @Transient
    private String token;
    public User() {
        super();
    }

    public User(Long personId, String name, String lastName, String dni, String email, String phoneNumber, LocalDate birthdate, Boolean status, Address address, String userName, String password, Role role, Branch branch, String token) {
        super(personId, name, lastName, dni, email, phoneNumber, birthdate, status, address);
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.branch = branch;
        this.token = token;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public String getToken() {
        return token;
    }
    public void setToken (String token){
        this.token=token;
    }
}
