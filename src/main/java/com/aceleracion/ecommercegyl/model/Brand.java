package com.aceleracion.ecommercegyl.model;

import javax.persistence.*;

@Entity

public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long brandId;
    private String name;
    private Boolean status;
    public Brand() {

    }

    public Brand(Long brandId, String name, Boolean status) {
        this.brandId = brandId;
        this.name = name;
        this.status = status;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
