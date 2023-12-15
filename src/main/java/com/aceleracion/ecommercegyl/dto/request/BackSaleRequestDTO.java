package com.aceleracion.ecommercegyl.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

public class BackSaleRequestDTO {

    @Size(min = 10, message = "debe ingrear al menos 10 caracteres.")
    @NotBlank(message = "Por favor ingrese un motivo.")
    private String description;
    @NotNull(message = "debe ingresar el id de la factura.")
    private Long invoiceId;
    @Pattern(regexp = "^\\d*$", message = "El dni no puede contener letras.")
    @Size(min = 7, message = "El Dni no puede ser inferior a 1 millon.")
    private String userDni;
    @NotNull(message = "debe ingresar al menos un producto.")
    private List<Long> productsCode;

    public BackSaleRequestDTO() {
    }

    public BackSaleRequestDTO(String description, Long invoiceId, String userDni, List<Long> productsCode) {
        this.description = description;
        this.invoiceId = invoiceId;
        this.userDni = userDni;
        this.productsCode = productsCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getUserDni() {
        return userDni;
    }

    public void setUserDni(String userDni) {
        this.userDni = userDni;
    }

    public List<Long> getProductsCode() {
        return productsCode;
    }

    public void setProductsCode(List<Long> productsCode) {
        this.productsCode = productsCode;
    }
}
