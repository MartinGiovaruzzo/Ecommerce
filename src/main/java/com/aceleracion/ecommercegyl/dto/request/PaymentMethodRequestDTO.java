package com.aceleracion.ecommercegyl.dto.request;


import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


public class PaymentMethodRequestDTO {

    @Size(min = 3, message = "Debe ingresar un nombre mayor a dos caracteres")
    @Pattern(regexp = "[A-Za-z]+", message = "El nombre solo puede contener letras")
    private String paymentMethodName;

    public PaymentMethodRequestDTO() {
    }

    public PaymentMethodRequestDTO(String paymentMethodName) {
        this.paymentMethodName = paymentMethodName;
    }

    public String getPaymentMethodName() {
        return paymentMethodName;
    }

    public void setPaymentMethodName(String paymentMethodName) {
        this.paymentMethodName = paymentMethodName;
    }
}
