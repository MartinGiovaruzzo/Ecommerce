package com.aceleracion.ecommercegyl.model;

import javax.persistence.*;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;
    private String street;
    private String number;
    @ManyToOne
    @JoinColumn(name = "cityId")
    private City city;
    private Boolean status;
    public Address() {
    }

    public Address(Long addressId, String street, String number, City city, Boolean status) {
        this.addressId = addressId;
        this.street = street;
        this.number = number;
        this.city = city;
        this.status = status;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
