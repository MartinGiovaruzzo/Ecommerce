package com.aceleracion.ecommercegyl.dto.response;

import java.time.LocalDate;


public class BackSaleResponseDTO {
    private Long backSaleId;
    private LocalDate date;
    private String description;
    private InvoiceResponseDTO invoiceSaleResponseDTO;
    private InvoiceResponseDTO invoiceBackSaleResponseDTO;

    public BackSaleResponseDTO() {
    }

    public BackSaleResponseDTO(Long backSaleId, LocalDate date, String description, InvoiceResponseDTO invoiceSaleResponseDTO, InvoiceResponseDTO invoiceBackSaleResponseDTO) {
        this.backSaleId = backSaleId;
        this.date = date;
        this.description = description;
        this.invoiceSaleResponseDTO = invoiceSaleResponseDTO;
        this.invoiceBackSaleResponseDTO = invoiceBackSaleResponseDTO;
    }

    public InvoiceResponseDTO getInvoiceSaleResponseDTO() {
        return invoiceSaleResponseDTO;
    }

    public void setInvoiceSaleResponseDTO(InvoiceResponseDTO invoiceSaleResponseDTO) {
        this.invoiceSaleResponseDTO = invoiceSaleResponseDTO;
    }

    public InvoiceResponseDTO getInvoiceBackSaleResponseDTO() {
        return invoiceBackSaleResponseDTO;
    }

    public void setInvoiceBackSaleResponseDTO(InvoiceResponseDTO invoiceBackSaleResponseDTO) {
        this.invoiceBackSaleResponseDTO = invoiceBackSaleResponseDTO;
    }

    public Long getBackSaleId() {
        return backSaleId;
    }

    public void setBackSaleId(Long backSaleId) {
        this.backSaleId = backSaleId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
