package com.aceleracion.ecommercegyl.dto.response;

public class BranchResponseDTO {
    private Long branchId;
    private String branchName;
    private BranchTypeResponseDTO branchTypeResponseDTO;
    private String phoneNumber;
    private Boolean status;
    private AddressResponseDTO addressResponseDTO;

    public BranchResponseDTO() {
    }

    public BranchResponseDTO(Long branchId, String branchName, BranchTypeResponseDTO branchTypeResponseDTO, String phoneNumber, Boolean status, AddressResponseDTO addressResponseDTO) {
        this.branchId = branchId;
        this.branchName = branchName;
        this.branchTypeResponseDTO = branchTypeResponseDTO;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.addressResponseDTO = addressResponseDTO;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public BranchTypeResponseDTO getBranchTypeResponseDTO() {
        return branchTypeResponseDTO;
    }

    public void setBranchTypeResponseDTO(BranchTypeResponseDTO branchTypeResponseDTO) {
        this.branchTypeResponseDTO = branchTypeResponseDTO;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public AddressResponseDTO getAddressResponseDTO() {
        return addressResponseDTO;
    }

    public void setAddressResponseDTO(AddressResponseDTO addressResponseDTO) {
        this.addressResponseDTO = addressResponseDTO;
    }
}