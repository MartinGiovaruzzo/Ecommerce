package com.aceleracion.ecommercegyl.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long branchId;
    private String branchName;
    private String phoneNumber;
    private Boolean status;
    @OneToOne
    @JoinColumn(name = "addressId")
    private Address address;
    @ManyToOne
    @JoinColumn(name = "branchTypeId")
    private BranchType branchType;
    @OneToMany
    private List<Product> products;

    public Branch() {
        this.products=new ArrayList<>();
    }

    public Branch(Long branchId, String branchName, String phoneNumber, Boolean status, Address address, BranchType branchType, List<Product> products) {
        this.branchId = branchId;
        this.branchName = branchName;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.address = address;
        this.branchType = branchType;
        this.products = products;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public BranchType getBranchType() {
        return branchType;
    }

    public void setBranchType(BranchType branchType) {
        this.branchType = branchType;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
