package com.aceleracion.ecommercegyl.model;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
public class BackSale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long backSaleId;
    private LocalDate date;
    @OneToOne
    @JoinColumn(name = "invoiceSaleId")
    private Invoice invoiceSale;
    private String description;
    @OneToOne
    @JoinColumn(name = "invoiceBackSaleId")
    private Invoice invoiceBackSale;

    public BackSale() {
    }

    public BackSale(Long backSaleId, LocalDate date, Invoice invoiceBackSale, String description, Invoice invoiceSale) {
        this.backSaleId = backSaleId;
        this.date = date;
        this.invoiceBackSale = invoiceBackSale;
        this.description = description;
        this.invoiceSale = invoiceSale;
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

    public Invoice getStatus() {
        return invoiceBackSale;
    }

    public void setStatus(Invoice invoiceBackSale) {
        this.invoiceBackSale = invoiceBackSale;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Invoice getInvoiceSale() {
        return invoiceSale;
    }

    public void setInvoiceSale(Invoice invoiceSale) {
        this.invoiceSale = invoiceSale;
    }

    public Invoice getInvoiceBackSale() {
        return invoiceBackSale;
    }

    public void setInvoiceBackSale(Invoice invoiceBackSale) {
        this.invoiceBackSale = invoiceBackSale;
    }

}
