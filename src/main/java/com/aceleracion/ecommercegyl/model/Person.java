package com.aceleracion.ecommercegyl.model;

import javax.persistence.*;
import java.time.LocalDate;


@MappedSuperclass
public abstract class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long personId;
    private String name;
    private String lastName;
    private String dni;
    private String email;
    private String phoneNumber;
    private LocalDate birthdate;
    private Boolean status;
    @ManyToOne
    @JoinColumn(name = "addressId")
    private Address address;


    protected Person() {
    }

    protected Person(Long personId, String name, String lastName, String dni, String email, String phoneNumber, LocalDate birthdate, Boolean status, Address address) {
        this.personId = personId;
        this.name = name;
        this.lastName = lastName;
        this.dni = dni;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.birthdate = birthdate;
        this.status = true;
        this.address = address;
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

}
