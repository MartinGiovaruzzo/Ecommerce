package com.aceleracion.ecommercegyl.repository;

import com.aceleracion.ecommercegyl.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.Date;
import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice,Long> {

    List<Invoice> findByPaymentMethodPaymentMethodId(Long paymentMethodId);

    List<Invoice> findByInvoiceTypeInvoiceTypeId(Long invoiceTypeId);

    List<Invoice> findInvoicesByCustomerDni(String dni);

    List<Invoice> findInvoicesByUserDni(String dni);

    List<Invoice> findInvoicesByBranchBranchId(Long branchId);

    @Query( "Select i from Invoice i where i.invoiceType.invoiceTypeId < 4 ")
    List<Invoice>findInvoicesByInvoiceTypesId();

    @Query("Select i from Invoice i where i.invoiceType.invoiceTypeId=4")
    List<Invoice>findCreditNote();
    List<Invoice> findByDateBetween(Date objStartDate, Date objEndDate);


}