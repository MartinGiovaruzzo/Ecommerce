package com.aceleracion.ecommercegyl.dto.request;


import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


public class InvoiceTypeRequestDTO {

    @Size(min = 3, message = "Debe ingresar un nombre mayor a 2 caracteres.")
    @Pattern(regexp = "[A-Za-z]+", message = "El nombre solo puede contener letras")
    private String invoiceName;

    public InvoiceTypeRequestDTO() {
    }

    public InvoiceTypeRequestDTO(String invoiceName) {
        this.invoiceName = invoiceName;
    }

    public String getInvoiceName() {
        return invoiceName;
    }

    public void setInvoiceName(String invoiceName) {
        this.invoiceName = invoiceName;
    }
}
