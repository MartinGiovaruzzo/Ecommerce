package com.aceleracion.ecommercegyl.dto.request;


import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.*;
import java.time.LocalDate;


public class CustomerRequestDTO {

    private Long customerId;

    @Size(min = 3, message = "Debe ingresar un nombre mayor a 2 caracteres.")
    @Pattern(regexp = "[A-Za-z]+", message = "El nombre solo puede contener letras")
    private String name;

    @Size(min = 3, message = "Debe ingresar un nombre mayor a 2 caracteres.")
    @Pattern(regexp = "[A-Za-z]+", message = "El apellido solo puede contener letras")
    private String lastName;

    @Pattern(regexp = "^\\d*$", message = "El dni no puede contener letras.")
    @Size(min = 7, message = "El Dni no puede ser inferior a 1 millon.")
    private String dni;

    @Email
    @Size(min = 6, message = "El Email no puede tener menos de 6 caracteres.")
    private String email;


    @Pattern(regexp = "^\\d*$", message = "El numero de telefono no puede contener letras.")
    @Size(min = 10, message = "El Telefono no puede tener menos  de 10 digitos.")
    private String phoneNumber;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "La fecha debe ser anterior a la fecha actual")
    @NotNull(message = "La fecha no debe ser nula")
    private LocalDate birthdate;

    private AddressRequestDTO addressRequestDTO;

    public CustomerRequestDTO() {
    }

    public CustomerRequestDTO(Long customerId, String name, String lastName, String dni, String email, String phoneNumber, LocalDate birthdate, AddressRequestDTO addressRequestDTO) {
        this.customerId = customerId;
        this.name = name;
        this.lastName = lastName;
        this.dni = dni;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.birthdate = birthdate;
        this.addressRequestDTO = addressRequestDTO;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
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

    public AddressRequestDTO getAddressRequestDTO() {
        return addressRequestDTO;
    }

    public void setAddressRequestDTO(AddressRequestDTO addressRequestDTO) {
        this.addressRequestDTO = addressRequestDTO;
    }
}
