package com.aceleracion.ecommercegyl.dto.request;



import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.*;
import java.time.LocalDate;



public class UserRequestDTO {


    @Size(min = 3, message = "Debe ingresar un nombre mayor a 3 caracteres.")
    @Pattern(regexp = "[A-Za-z]+", message = "El nombre solo puede contener letras")
    private String name;

    @Size(min = 3, message = "Debe ingresar un nombre mayor a 3 caracteres.")
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

    @Size(min = 3, message = "Debe ingresar un nombre mayor a 3 caracteres.")
    @Pattern(regexp = "[A-Za-z]+", message = "El Nombre de usuario solo puede contener letras")
    private String userName;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).*$",
            message = "La contraseña debe contener al menos una mayúscula, una minúscula, un dígito y un carácter especial")
    @Size(min = 6, message = "Debe ingresar una contraseña mayor a 6 caracteres.")
    private String password;

    @NotNull(message = "Debe ingresar el id del rol")
    @Positive(message = "El id del rol debe ser mayor a 0.")
    private Long idRol;

    @NotNull(message = "Debe ingresar el id del sucursal")
    @Positive(message = "El id de la sucursal debe ser mayor a 0.")
    private Long branchId;
    @Size(min = 3, message = "Debe ingresar una direccion mayor a 3 caracteres.")
    @NotNull(message = "Debe ingresar la direccion")
    private String street;

    @NotNull(message = "Debe ingresar la altura de la calle")
    private String number;

    @NotNull(message = "Debe ingresar una ciudad")
    @Positive(message = "El id de la ciudad debe ser mayor a 0.")
    private Long cityId;

    public UserRequestDTO() {
    }

    public UserRequestDTO(String name, String lastName, String dni, String email, String phoneNumber, LocalDate birthdate, String userName, String password, Long idRol, Long branchId, String street, String number, Long cityId) {
        this.name = name;
        this.lastName = lastName;
        this.dni = dni;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.birthdate = birthdate;
        this.userName = userName;
        this.password = password;
        this.idRol = idRol;
        this.branchId = branchId;
        this.street = street;
        this.number = number;
        this.cityId = cityId;
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

    public Long getIdRol() {
        return idRol;
    }

    public void setIdRol(Long idRol) {
        this.idRol = idRol;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }
}
