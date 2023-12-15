package com.aceleracion.ecommercegyl.model;

import javax.persistence.*;

@Entity
public class BranchType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long branchTypeId;
    private String branchType;
    private Boolean status;

    public BranchType() {
    }
    public BranchType(Long branchTypeId, String typeOfBranch) {
        this.branchTypeId = branchTypeId;
        this.branchType = typeOfBranch;
        this.status = true;
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
