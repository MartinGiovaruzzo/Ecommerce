package com.aceleracion.ecommercegyl.dto.request;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserSignInRequestDTO {

    @Size(min = 3, message = "Debe ingresar un nombre mayor a 3 caracteres.")
    @Pattern(regexp = "[A-Za-z]+", message = "El Nombre de usuario solo puede contener letras")
    private String userName;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).*$",
            message = "La contraseña debe contener al menos una mayúscula, una minúscula, un dígito y un carácter especial")
    @Size(min = 6, message = "Debe ingresar una contraseña mayor a 6 caracteres.")
    private String password;

    public UserSignInRequestDTO() {
    }

    public UserSignInRequestDTO(String userName, String password) {
        this.userName = userName;
        this.password = password;
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

}
