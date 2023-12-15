package com.aceleracion.ecommercegyl.dto.response;

public class InvoiceTypeResponseDTO {
    private Long invoiceTypeId;
    private String invoiceName;

    public InvoiceTypeResponseDTO() {
    }

    public InvoiceTypeResponseDTO(Long invoiceTypeId, String invoiceName) {
        this.invoiceTypeId = invoiceTypeId;
        this.invoiceName = invoiceName;
    }

    public Long getInvoiceTypeId() {
        return invoiceTypeId;
    }

    public void setInvoiceTypeId(Long invoiceTypeId) {
        this.invoiceTypeId = invoiceTypeId;
    }

    public String getInvoiceName() {
        return invoiceName;
    }

    public void setInvoiceName(String invoiceName) {
        this.invoiceName = invoiceName;
    }
}
