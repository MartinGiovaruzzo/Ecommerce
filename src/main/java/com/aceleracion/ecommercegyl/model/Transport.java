package com.aceleracion.ecommercegyl.model;



import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Transport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transportId;
    private Date date;

    @ManyToOne()
    @JoinColumn (name="originBranchId")
    private Branch originBranch;
    @ManyToOne()
    @JoinColumn (name="targetBranchId")
    private Branch targetBranch;
    @ManyToMany
    @JoinColumn (name="productId")
    private List<Product> products;

    public Transport() {
        this.products = new ArrayList<>();
    }

    public Transport(Long transportId, Date date, Branch originBranch, Branch targetBranch, List<Product> products) {
        this.transportId = transportId;
        this.date = date;
        this.originBranch = originBranch;
        this.targetBranch = targetBranch;
        this.products = products;
    }

    public Long getTransportId() {
        return transportId;
    }

    public void setTransportId(Long transportId) {
        this.transportId = transportId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Branch getOriginBranch() {
        return originBranch;
    }

    public void setOriginBranch(Branch originBranch) {
        this.originBranch = originBranch;
    }

    public Branch getTargetBranch() {
        return targetBranch;
    }

    public void setTargetBranch(Branch targetBranch) {
        this.targetBranch = targetBranch;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
