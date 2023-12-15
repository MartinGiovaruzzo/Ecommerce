package com.aceleracion.ecommercegyl.dto.response;


public class BranchTypeResponseDTO {
    private Long branchTypeId;
    private String branchType;
    private Boolean status;

    public BranchTypeResponseDTO() {
    }

    public BranchTypeResponseDTO(Long branchTypeId, String branchType, Boolean status) {
        this.branchTypeId = branchTypeId;
        this.branchType = branchType;
        this.status = status;
    }

    public Long getBranchTypeId() {
        return branchTypeId;
    }

    public void setBranchTypeId(Long branchTypeId) {
        this.branchTypeId = branchTypeId;
    }

    public String getBranchType() {
        return branchType;
    }

    public void setBranchType(String branchType) {
        this.branchType = branchType;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
