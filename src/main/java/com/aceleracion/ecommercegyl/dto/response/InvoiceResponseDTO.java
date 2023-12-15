package com.aceleracion.ecommercegyl.dto.response;

import com.aceleracion.ecommercegyl.model.User;

import java.time.LocalDate;
import java.util.List;

public class InvoiceResponseDTO {
    private Long invoiceId;
    private LocalDate date;
    private BranchResponseDTO branchResponseDTO;
    private CustomerResponseDTO customerResponseDTO;
    private InvoiceTypeResponseDTO invoiceTypeResponseDTO;
    private PaymentMethodResponseDTO paymentMethodResponseDTO;
    private User userResponseDTO;
    private DiscountRateResponseDTO discountRateResponseDTO;
    private List<ProductResponseDTO> productsListResponseDTO;

    public InvoiceResponseDTO() {

    }

    public InvoiceResponseDTO(Long invoiceId, LocalDate date, BranchResponseDTO branchResponseDTO,
                              CustomerResponseDTO customerResponseDTO, InvoiceTypeResponseDTO invoiceTypeResponseDTO,
                              PaymentMethodResponseDTO paymentMethodResponseDTO, User userResponseDTO,
                              DiscountRateResponseDTO discountRateResponseDTO, List<ProductResponseDTO> productsListResponseDTO) {
        this.invoiceId = invoiceId;
        this.date = date;
        this.branchResponseDTO = branchResponseDTO;
        this.customerResponseDTO = customerResponseDTO;
        this.invoiceTypeResponseDTO = invoiceTypeResponseDTO;
        this.paymentMethodResponseDTO = paymentMethodResponseDTO;
        this.userResponseDTO = userResponseDTO;
        this.discountRateResponseDTO = discountRateResponseDTO;
        this.productsListResponseDTO = productsListResponseDTO;
    }

    public List<ProductResponseDTO> getProductsResponseDTOList() {
        return productsListResponseDTO;
    }

    public void setProductsResponseDTOList(List<ProductResponseDTO> productsListResponseDTO) {
        this.productsListResponseDTO = productsListResponseDTO;
    }

    public User getUserResponseDTO() {
        return userResponseDTO;
    }

    public void setUserResponseDTO(User userResponseDTO) {
        this.userResponseDTO = userResponseDTO;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BranchResponseDTO getBranchResponseDTO() {
        return branchResponseDTO;
    }

    public void setBranchResponseDTO(BranchResponseDTO branchResponseDTO) {
        this.branchResponseDTO = branchResponseDTO;
    }

    public CustomerResponseDTO getCustomerResponseDTO() {
        return customerResponseDTO;
    }

    public void setCustomerResponseDTO(CustomerResponseDTO customerResponseDTO) {
        this.customerResponseDTO = customerResponseDTO;
    }

    public InvoiceTypeResponseDTO getInvoiceTypeResponseDTO() {
        return invoiceTypeResponseDTO;
    }

    public void setInvoiceTypeResponseDTO(InvoiceTypeResponseDTO invoiceTypeResponseDTO) {
        this.invoiceTypeResponseDTO = invoiceTypeResponseDTO;
    }

    public PaymentMethodResponseDTO getPaymentMethodResponseDTO() {
        return paymentMethodResponseDTO;
    }

    public void setPaymentMethodResponseDTO(PaymentMethodResponseDTO paymentMethodResponseDTO) {
        this.paymentMethodResponseDTO = paymentMethodResponseDTO;
    }

    public DiscountRateResponseDTO getDiscountRateResponseDTO() {
        return discountRateResponseDTO;
    }

    public void setDiscountRateResponseDTO(DiscountRateResponseDTO discountRateResponseDTO) {
        this.discountRateResponseDTO = discountRateResponseDTO;
    }

}
