package com.aceleracion.ecommercegyl.model;

import javax.persistence.*;

@Entity
public class ProductType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productTypeId;
    private String productType;
    private Boolean status;

    public ProductType() {
    }

    public ProductType(Long productTypeId, String typeOfProduct) {
        this.productTypeId = productTypeId;
        this.productType = typeOfProduct;
        this.status = true;
    }

    public Long getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Long productTypeId) {
        this.productTypeId = productTypeId;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
