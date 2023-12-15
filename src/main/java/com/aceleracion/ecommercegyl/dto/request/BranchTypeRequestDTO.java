package com.aceleracion.ecommercegyl.dto.request;


import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


public class BranchTypeRequestDTO {
    @Size(min = 3, message = "Debe ingresar un nombre mayor a 2 caracteres.")
    @Pattern(regexp = "[A-Za-z]+", message = "El tipo de sucursal solo puede contener letras")
    private String branchType;

    public BranchTypeRequestDTO() {
    }

    public BranchTypeRequestDTO(String branchType) {
        this.branchType = branchType;
    }

    public String getBranchType() {
        return branchType;
    }

    public void setBranchType(String branchType) {
        this.branchType = branchType;
    }
}
