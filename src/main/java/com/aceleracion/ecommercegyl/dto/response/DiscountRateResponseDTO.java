package com.aceleracion.ecommercegyl.dto.response;

public class DiscountRateResponseDTO {
    private Long discountRateId;
    private String discountName;
    private Double discountPercentage;
    private Double minimumAmount;
    private Double maxAmount;
    private Double maxDiscount;
    private Boolean status;

    public DiscountRateResponseDTO() {
    }

    public DiscountRateResponseDTO(Long discountId, String discountName, Double discountPercentage, Double minimumAmount, Double maxAmount, Double maxDiscount, Boolean status) {
        this.discountRateId = discountId;
        this.discountName = discountName;
        this.discountPercentage = discountPercentage;
        this.minimumAmount = minimumAmount;
        this.maxAmount = maxAmount;
        this.maxDiscount = maxDiscount;
        this.status = status;
    }

    public Long getDiscountRateId() {
        return discountRateId;
    }

    public void setDiscountRateId(Long discountRateId) {
        this.discountRateId = discountRateId;
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}

