package com.aceleracion.ecommercegyl.repository;

import com.aceleracion.ecommercegyl.model.InvoiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceTypeRepository extends JpaRepository<InvoiceType, Long> {

    InvoiceType findInvoiceTypeByInvoiceName(String invoiceName);

    List<InvoiceType> findAllByStatusTrue();
}
