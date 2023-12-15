package com.aceleracion.ecommercegyl.dto.response;


public class ProductResponseDTO {
    private Long productCode;
    private String model;
    private Double sellingPrice;
    private Double purchasePrice;
    private Boolean status;
    private BrandResponseDTO brandResponseDTO;
    private BranchResponseDTO branchResponseDTO;
    private ProductTypeResponseDTO productTypeResponseDTO;

    public ProductResponseDTO() {
    }

    public ProductResponseDTO(Long productCode, String model, Double sellingPrice, Double purchasePrice, Boolean status, BrandResponseDTO brandResponseDTO, BranchResponseDTO branchResponseDTO, ProductTypeResponseDTO productTypeResponseDTO) {
        this.productCode = productCode;
        this.model = model;
        this.sellingPrice = sellingPrice;
        this.purchasePrice = purchasePrice;
        this.status = status;
        this.brandResponseDTO = brandResponseDTO;
        this.branchResponseDTO = branchResponseDTO;
        this.productTypeResponseDTO = productTypeResponseDTO;

    }

    public Long getProductCode() {
        return productCode;
    }

    public void setProductCode(Long productCode) {
        this.productCode = productCode;
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public BrandResponseDTO getBrandResponseDTO() {
        return brandResponseDTO;
    }

    public void setBrandResponseDTO(BrandResponseDTO brandResponseDTO) {
        this.brandResponseDTO = brandResponseDTO;
    }

    public BranchResponseDTO getBranchResponseDTO() {
        return branchResponseDTO;
    }

    public void setBranchResponseDTO(BranchResponseDTO branchResponseDTO) {
        this.branchResponseDTO = branchResponseDTO;
    }

    public ProductTypeResponseDTO getProductTypeResponseDTO() {
        return productTypeResponseDTO;
    }

    public void setProductTypeResponseDTO(ProductTypeResponseDTO productTypeResponseDTO) {
        this.productTypeResponseDTO = productTypeResponseDTO;
    }
}