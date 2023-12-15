package com.aceleracion.ecommercegyl.dto.request;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class BranchRequestDTO {


    @Size(min = 3, message = "El nombre de la sucursal debe ser mayor a 2 caracteres.")
    @Pattern(regexp = "[A-Za-z]+", message = "El nombre de la sucursal solo puede contener letras")
    private String branchName;
    @Pattern(regexp = "^\\d*$", message = "El numero de telefono no puede contener letras.")
    @Size(min = 10, message = "El Telefono no puede tener menos  de 10 digitos.")
    private String phoneNumber;
    @NotNull(message = "Debe ingresar el id del tipo de sucursal")
    @Positive(message = "El el id del tipo de sucursal debe ser mayor a 0.")
    private Long branchTypeId;

    private AddressRequestDTO addressReqDTO;

    public BranchRequestDTO() {
    }

    public BranchRequestDTO(String branchName, String phoneNumber, Long branchTypeId, AddressRequestDTO addressReqDTO) {

        this.branchName = branchName;
        this.phoneNumber = phoneNumber;
        this.branchTypeId = branchTypeId;
        this.addressReqDTO = addressReqDTO;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getBranchTypeId() {
        return branchTypeId;
    }

    public void setBranchTypeId(Long branchTypeId) {
        this.branchTypeId = branchTypeId;
    }

    public AddressRequestDTO getAddressReqDTO() {
        return addressReqDTO;
    }

    public void setAddressReqDTO(AddressRequestDTO addressReqDTO) {
        this.addressReqDTO = addressReqDTO;
    }
}
