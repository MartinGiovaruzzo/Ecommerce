package com.aceleracion.ecommercegyl.dto.request;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserFindRequestDTO {

    @Size(min = 3, message = "Debe ingresar un nombre mayor a 3 caracteres.")
    @Pattern(regexp = "[A-Za-z]+", message = "El nombre solo puede contener letras")
    private String name;

    @Size(min = 3, message = "Debe ingresar un nombre mayor a 3 caracteres.")
    @Pattern(regexp = "[A-Za-z]+", message = "El apellido solo puede contener letras")
    private String lastName;

    public UserFindRequestDTO() {
    }

    public UserFindRequestDTO(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
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
}
