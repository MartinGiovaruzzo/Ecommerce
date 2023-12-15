package com.aceleracion.ecommercegyl.dto.request;

import javax.validation.constraints.*;

public class DiscountRateRequestDTO {
    @Size(min = 3, message = "Debe ingresar un nombre mayor a 3 caracteres.")
    @NotNull(message = "No puede tener un nombre nulo")
    private String discountName;

    @NotNull(message = "debe ingresar un valor de descuento")
    @DecimalMin(value = "0.1", inclusive = true, message = "El porcentaje de descuento debe ser mayor o igual a 0.1")
    @DecimalMax(value = "100.0", inclusive = true, message = "El porcentaje de descuento debe ser menor o igual a 100.0")
    private Double discountPercentage;
    @NotNull(message = "debe ingresar un valor minimo de descuento")
    @DecimalMin(value = "0.0", inclusive = true, message = "El valor minimo de descuento debe ser mayor o igual a 0.0")
    @DecimalMax(value = "30000000.0", inclusive = true, message = "El valor minimo de descuento debe ser menor o igual a 30000000.0")
    private Double minimumAmount;
    @NotNull(message = "debe ingresar un valor maximo de compra")
    @DecimalMin(value = "0.0", inclusive = true, message = "El valor maximo de compra debe ser mayor o igual a 0.0")
    @DecimalMax(value = "30000000.0", inclusive = true, message = "El valor maximo de compra debe ser menor o igual a 30000000.0")
    private Double maxAmount;
    @NotNull(message = "debe ingresar un valor maximo de descuento")
    @DecimalMin(value = "0.0", inclusive = true, message = "El valor maximo de descuento debe ser mayor o igual a 0.0")
    @DecimalMax(value = "30000000.0", inclusive = true, message = "El valor maximo de descuento debe ser menor o igual a 30000000.0")
    private Double maxDiscount;

    public DiscountRateRequestDTO() {
    }

    public DiscountRateRequestDTO(String discountName, Double discountPercentage, Double minimumAmount, Double maxAmount, Double maxDiscount) {
        this.discountName = discountName;
        this.discountPercentage = discountPercentage;
        this.minimumAmount = minimumAmount;
        this.maxAmount = maxAmount;
        this.maxDiscount = maxDiscount;
    }

    public String getDiscountName() {
        return discountName;
    }

    public void setDiscountName(String discountName) {
        this.discountName = discountName;
    }

    public Double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(Double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public Double getMinimumAmount() {
        return minimumAmount;
    }

    public void setMinimumAmount(Double minimumAmount) {
        this.minimumAmount = minimumAmount;
    }

    public Double getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(Double maxAmount) {
        this.maxAmount = maxAmount;
    }

    public Double getMaxDiscount() {
        return maxDiscount;
    }

    public void setMaxDiscount(Double maxDiscount) {
        this.maxDiscount = maxDiscount;
    }
}


