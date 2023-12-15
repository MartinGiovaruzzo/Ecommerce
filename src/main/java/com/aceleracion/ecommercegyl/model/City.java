package com.aceleracion.ecommercegyl.model;

import javax.persistence.*;

@Entity
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cityId;
    private String cityName;
    private Boolean status;
    @ManyToOne()
    @JoinColumn(name = "provinceId")
    private Province province;
    public City() {
    }

    public City(Long cityId, String cityName, Boolean status, Province province) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.status = status;
        this.province = province;
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

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }
}
