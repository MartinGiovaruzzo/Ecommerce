package com.aceleracion.ecommercegyl.dto.request;

import javax.validation.constraints.*;
import java.util.List;

public class InvoiceRequestDTO {
    @NotNull(message = "Debe ingresar la sucursal.")
    @Positive(message = "El Id de la sucursal debe ser mayor a 0.")
    private Long branchId;
    @Pattern(regexp = "^\\d*$", message = "El Dni del cliente no puede contener letras.")
    @Size(min = 7, message = "El Dni del cliente no puede ser inferior a 1 millon.")
    private String customerDni;
    @NotNull(message = "Debe ingresar el tipo de factura.")
    @Positive(message = "El Id del tipo de factura debe ser mayor a 0.")
    private Long invoiceTypeId;
    @NotNull(message = "Debe ingresar un metodo de pago.")
    @Positive(message = "El Id del metodo de pago debe ser mayor a 0.")
    private Long paymentMethodId;
    @Pattern(regexp = "^\\d*$", message = "El dni del usuario no puede contener letras.")
    @Size(min = 7, message = "El Dni el usuario no puede ser inferior a 1 millon.")
    private String userDni;
    @NotNull(message = "Debe ingresar un tipo de descuento.")
    @Positive(message = "El Id del tipo de descuento debe ser mayor a 0.")
    private Long discountRateId;

    @NotNull(message = "Debe ingresar al menos un Producto.")
    private List<Long> productsListCode;

    public InvoiceRequestDTO() {}

    public InvoiceRequestDTO(Long branchId, String customerDni, Long invoiceTypeId, Long paymentMethodId, String userDni, Long discountRateId, List<Long> productsListCode) {
        this.branchId = branchId;
        this.customerDni = customerDni;
        this.invoiceTypeId = invoiceTypeId;
        this.paymentMethodId = paymentMethodId;
        this.userDni = userDni;
        this.discountRateId = discountRateId;
        this.productsListCode = productsListCode;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public String getCustomerDni() {
        return customerDni;
    }

    public void setCustomerDni(String customerDni) {
        this.customerDni = customerDni;
    }

    public Long getInvoiceTypeId() {
        return invoiceTypeId;
    }

    public void setInvoiceTypeId(Long invoiceTypeId) {
        this.invoiceTypeId = invoiceTypeId;
    }

    public Long getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(Long paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public String getUserDni() {
        return userDni;
    }

    public void setUserDni(String userDni) {
        this.userDni = userDni;
    }

    public Long getDiscountRateId() {
        return discountRateId;
    }

    public void setDiscountRateId(Long discountRateId) {
        this.discountRateId = discountRateId;
    }

    public List<Long> getProductsListCode() {
        return productsListCode;
    }

    public void setProductsListCode(List<Long> productsListCode) {
        this.productsListCode = productsListCode;
    }
}
