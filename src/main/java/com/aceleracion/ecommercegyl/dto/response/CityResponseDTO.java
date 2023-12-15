package com.aceleracion.ecommercegyl.dto.response;


public class CityResponseDTO {
    private Long cityId;
    private String cityName;
    private Boolean status;
    private ProvinceResponseDTO provinceResponseDTO;

    public CityResponseDTO() {
    }

    public CityResponseDTO(Long cityId, String cityName, Boolean status, ProvinceResponseDTO provinceResponseDTO) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.status = status;
        this.provinceResponseDTO = provinceResponseDTO;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public ProvinceResponseDTO getProvinceResponseDTO() {
        return provinceResponseDTO;
    }

    public void setProvinceResponseDTO(ProvinceResponseDTO provinceResponseDTO) {
        this.provinceResponseDTO = provinceResponseDTO;
    }
}
