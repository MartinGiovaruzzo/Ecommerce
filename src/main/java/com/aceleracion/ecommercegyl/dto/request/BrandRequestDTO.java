package com.aceleracion.ecommercegyl.dto.request;


import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


public class BrandRequestDTO {

    @Size(min = 3, message = "Debe ingresar un nombre mayor a dos caracteres")
    @Pattern(regexp = "[A-Za-z]+", message = "El nombre del producto solo puede contener letras")
    private String name;

    public BrandRequestDTO() {
    }

    public BrandRequestDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
