package com.aceleracion.ecommercegyl.dto.request;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;


public class CityRequestDTO {
    @Size(min = 3, message = "Debe ingresar un nombre mayor a tres caracteres.")
    @Pattern(regexp = "[A-Za-z]+", message = "El nombre de la Ciudad solo puede contener letras.")
    private String cityName;
    @NotNull(message = "Debe ingresar el id de la provincia.")
    @Positive(message = "El Id de la provincia debe ser mayor a 0.")
    private Long provinceId;

    public CityRequestDTO() {
    }

    public CityRequestDTO(String cityName, Long provinceId) {
        this.cityName = cityName;
        this.provinceId = provinceId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }
}
