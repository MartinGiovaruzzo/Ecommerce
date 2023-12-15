package com.aceleracion.ecommercegyl.dto.request;


import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


public class ProvinceRequestDTO {

    @Size(min = 3, message = "Debe ingresar un nombre mayor a tres caracteres")
    @Pattern(regexp = "[A-Za-z]+", message = "El nombre solo puede contener letras")
    private String provinceName;

    public ProvinceRequestDTO() {
    }

    public ProvinceRequestDTO(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }
}
