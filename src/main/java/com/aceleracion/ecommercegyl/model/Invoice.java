package com.aceleracion.ecommercegyl.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long invoiceId;
    private Date date;
    @ManyToOne
    @JoinColumn(name = "branchId")
    private Branch branch;
    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "invoiceTypeId")
    private InvoiceType invoiceType;
    @ManyToOne
    @JoinColumn(name = "paymentMethodId")
    private PaymentMethod paymentMethod;
    @ManyToOne
    @JoinColumn(name = "userDni")
    private User user;
    @ManyToOne
    @JoinColumn(name = "discountRateId")
    private DiscountRate discountRate;
    @ManyToMany
    @JoinColumn (name="productId")
    private List<Product> productsList;

    public Invoice() {}

    public Invoice(Long invoiceId, Date date, Branch branch, Customer customer, InvoiceType invoiceType, PaymentMethod paymentMethod, DiscountRate discountRate, User user, List<Product> productsList) {
        this.invoiceId = invoiceId;
        this.date = date;
        this.branch = branch;
        this.customer = customer;
        this.invoiceType = invoiceType;
        this.paymentMethod = paymentMethod;
        this.discountRate = discountRate;
        this.user = user;
        this.productsList = productsList;
    }

    public List<Product> getProductList() {
        return productsList;
    }

    public void setProductList(List<Product> productList) {
        this.productsList = productList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public InvoiceType getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(InvoiceType invoiceType) {
        this.invoiceType = invoiceType;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public DiscountRate getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(DiscountRate discountRate) {
        this.discountRate = discountRate;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "invoiceId=" + invoiceId +
                ", date=" + date +
                ", branch=" + branch +
                ", customer=" + customer +
                ", invoiceType=" + invoiceType +
                ", paymentMethod=" + paymentMethod +
                ", user=" + user +
                ", discountRate=" + discountRate +
                ", productsList=" + productsList +
                '}';
    }
}
