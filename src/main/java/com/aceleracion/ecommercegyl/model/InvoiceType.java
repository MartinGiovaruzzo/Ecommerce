package com.aceleracion.ecommercegyl.model;


import javax.persistence.*;

@Entity
public class InvoiceType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long invoiceTypeId;
    private String invoiceName;

    private Boolean status;

    public InvoiceType() {
    }

    public InvoiceType(Long invoiceTypeId, String invoiceName, Boolean status) {
        this.invoiceTypeId = invoiceTypeId;
        this.invoiceName = invoiceName;
        this.status = status;
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
