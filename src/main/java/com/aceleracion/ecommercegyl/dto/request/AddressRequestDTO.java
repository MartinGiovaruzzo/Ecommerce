package com.aceleracion.ecommercegyl.dto.request;


import javax.validation.constraints.*;

public class AddressRequestDTO {

    @Size(min = 3, message = "Debe ingresar un nombre mayor a tres caracteres")
    @NotEmpty(message = "Debe ingresar el nombre de la calle.")
    private String street;
    @NotBlank(message = "Debe ingresar algun numero")
    @NotEmpty(message = "debe ingresar la altura de la calle")
    @Pattern(regexp = "^\\d*$", message = "La altura de la calle no puede contener letras.")
    private String number;

    @NotNull(message = "Debe ingresar el Id de la ciudad.")
    @Positive(message = "El el el Id de la ciudad debe ser mayor a 0.")
    private Long cityId;

    public AddressRequestDTO() {
    }

    public AddressRequestDTO(String street, String number, Long cityId) {
        this.street = street;
        this.number = number;
        this.cityId = cityId;
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

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }
}

