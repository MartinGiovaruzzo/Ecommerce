package com.aceleracion.ecommercegyl.dto.request;


import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


public class RoleRequestDTO {

    @Size(min = 3, message = "Debe ingresar un nombre mayor a dos caracteres")
    @Pattern(regexp = "[A-Za-z]+", message = "El rol solo puede contener letras")
    private String roleName;

    public RoleRequestDTO() {
    }

    public RoleRequestDTO(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
