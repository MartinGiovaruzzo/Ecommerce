package com.aceleracion.ecommercegyl.dto.request;


import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


public class ProductTypeRequestDTO {

    @Size(min = 3, message = "Debe ingresar un nombre mayor a 2 caracteres.")
    @Pattern(regexp = "[A-Za-z]+", message = "El tipo de producto solo puede contener letras")
    private String productType;

    public ProductTypeRequestDTO() {
    }

    public ProductTypeRequestDTO(String productType) {
        this.productType = productType;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }
}
