package com.aceleracion.ecommercegyl.dto.request;


import javax.validation.constraints.*;


public class ProductRequestDTO {
    @NotNull(message = "Debe ingresar el codigo del producto")
    @Positive(message = "El codigo del producto debe ser mayor a 0.")
    private Long productCode;
    @NotNull(message = "Debe ingresar el id de marca")
    @Positive(message = "El id de la marca debe ser mayor a 0.")
    private Long brandId;
    @Size(min = 3, message = "Debe ingresar un nombre mayor a 2 caracteres.")
    @Pattern(regexp = "[A-Za-z]+", message = "El nombre del modelo solo puede contener letras.")
    private String model;
    @NotNull(message = "Debe ingresar un precio.")
    @Positive(message = "El precio debe ser mayor a 0.")
    private Double sellingPrice;
    @NotNull(message = "Debe ingresar un precio.")
    @Positive(message = "El precio debe ser mayor a 0.")
    private Double purchasePrice;
    @NotEmpty(message = "Debe ingresar la descripción.")
    @Size(min = 10, message = "Debe ingresar una descripcion mayor a 10 caracteres.")
    private String description;
     @NotNull(message = "Debe ingresar el id del tipo de producto.")
    @Positive(message = "El id del tipo de producto debe ser mayor a 0.")
    private Long productTypeId;
    @NotNull(message = "Debe ingresar el id de la sucursal.")
    @Positive(message = "El id de la sucursal debe ser mayor a 0.")
    private Long branchId;

    public ProductRequestDTO() {
    }

    public ProductRequestDTO(Long productCode, Long brandId, String model, Double sellingPrice, Double purchasePrice, String description, Long productTypeId, Long branchId) {
        this.productCode = productCode;
        this.brandId = brandId;
        this.model = model;
        this.sellingPrice = sellingPrice;
        this.purchasePrice = purchasePrice;
        this.description = description;

        this.productTypeId = productTypeId;
        this.branchId = branchId;
    }

    public Long getProductCode() {
        return productCode;
    }

    public void setProductCode(Long productCode) {
        this.productCode = productCode;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
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

    public Long getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Long productTypeId) {
        this.productTypeId = productTypeId;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }
}
