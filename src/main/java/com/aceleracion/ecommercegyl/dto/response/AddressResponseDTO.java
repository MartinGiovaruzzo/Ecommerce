package com.aceleracion.ecommercegyl.dto.response;


public class AddressResponseDTO {
    private String street;
    private String number;
    private CityResponseDTO cityResponseDTO;

    public AddressResponseDTO() {
    }

    public AddressResponseDTO(String street, String number, CityResponseDTO cityResponseDTO) {
        this.street = street;
        this.number = number;
        this.cityResponseDTO = cityResponseDTO;
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

    public CityResponseDTO getCityResponseDTO() {
        return cityResponseDTO;
    }

    public void setCityResponseDTO(CityResponseDTO cityResponseDTO) {
        this.cityResponseDTO = cityResponseDTO;
    }
}
