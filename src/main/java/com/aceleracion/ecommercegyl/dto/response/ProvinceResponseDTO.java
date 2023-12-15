package com.aceleracion.ecommercegyl.dto.response;


public class ProvinceResponseDTO {
    private Long provinceId;
    private String provinceName;
    private Boolean status;
    public ProvinceResponseDTO() {
    }

    public ProvinceResponseDTO(Long provinceId, String provinceName, Boolean status) {
        this.provinceId = provinceId;
        this.provinceName = provinceName;
        this.status = status;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
