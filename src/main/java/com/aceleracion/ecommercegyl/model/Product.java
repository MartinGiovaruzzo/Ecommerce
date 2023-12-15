package com.aceleracion.ecommercegyl.model;

import javax.persistence.*;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    @Column(unique = true)
    private Long productCode;
    @ManyToOne
    @JoinColumn(name = "brandId")
    private Brand brand;
    private String model;
    private Double sellingPrice;
    private Double purchasePrice;
    private String description;

    private Boolean status;
    @ManyToOne
    @JoinColumn(name = "productTypeId")
    private ProductType productType;

    @ManyToOne
    @JoinColumn(name = "branchId")
    private Branch branch;

    public Product() {

    }

    private Product(ProductBuilder builder) {
        this.productId = builder.productId;
        this.productCode = builder.productCode;
        this.brand = builder.brand;
        this.model = builder.model;
        this.sellingPrice = builder.sellingPrice;
        this.purchasePrice = builder.purchasePrice;
        this.description = builder.description;
        this.status = builder.status;
        this.productType = builder.productType;
        this.branch = builder.branch;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getProductCode() {
        return productCode;
    }

    public void setProductCode(Long productCode) {
        this.productCode = productCode;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }


    public static class ProductBuilder {
        private Long productId;
        private Long productCode;
        private Brand brand;
        private String model;
        private Double sellingPrice;
        private Double purchasePrice;
        private String description;
        private Boolean status;
        private ProductType productType;
        private Branch branch;


        public ProductBuilder productId(Long productId) {
            this.productId = productId;
            return this;
        }
        public ProductBuilder productCode(Long productCode) {
            this.productCode = productCode;
            return this;
        }
        public ProductBuilder brand(Brand brand) {
            this.brand = brand;
            return this;
        }
        public ProductBuilder model(String model) {
            this.model = model;
            return this;
        }
        public ProductBuilder sellingPrice(Double sellingPrice){
            this.sellingPrice = sellingPrice;
            return this;
        }
        public ProductBuilder purchasePrice(Double purchasePrice){
            this.purchasePrice = purchasePrice;
            return this;
        }
        public ProductBuilder description(String description){
            this.description = description;
            return this;
        }

        public ProductBuilder status(Boolean status){
            this.status = status;
            return this;
        }
        public ProductBuilder productType(ProductType productType){
            this.productType = productType;
            return this;
        }
        public ProductBuilder branch(Branch branch){
            this.branch = branch;
            return this;
        }
        public Product build(){
            return new Product(this);
        }

    }
}
