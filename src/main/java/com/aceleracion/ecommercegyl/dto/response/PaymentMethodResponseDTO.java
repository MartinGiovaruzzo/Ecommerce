package com.aceleracion.ecommercegyl.dto.response;


public class PaymentMethodResponseDTO {
    private Long paymentMethodId;
    private String paymentMethodName;

    public PaymentMethodResponseDTO() {
    }

    public PaymentMethodResponseDTO(Long paymentMethodId, String paymentMethodName) {
        this.paymentMethodId = paymentMethodId;
        this.paymentMethodName = paymentMethodName;
    }

    public Long getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(Long paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public String getPaymentMethodName() {
        return paymentMethodName;
    }

    public void setPaymentMethodName(String paymentMethodName) {
        this.paymentMethodName = paymentMethodName;
    }
}
