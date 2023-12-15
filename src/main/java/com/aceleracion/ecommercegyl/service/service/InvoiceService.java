package com.aceleracion.ecommercegyl.service.service;

import com.aceleracion.ecommercegyl.dto.request.InvoiceRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.InvoiceResponseDTO;
import com.aceleracion.ecommercegyl.model.Invoice;

import java.text.ParseException;
import java.util.List;

public interface InvoiceService {
    InvoiceResponseDTO createInvoice(InvoiceRequestDTO invoiceRequestDTO);
    InvoiceResponseDTO findInvoiceById(Long invoiceId);
    Invoice findById(Long invoiceId);
    List<InvoiceResponseDTO> findAllInvoices();
    List<InvoiceResponseDTO> findInvoicesByPaymentMethod(Long paymentMethodId);
    List<InvoiceResponseDTO> findInvoicesByInvoiceType(Long invoiceTypeId);
    List<InvoiceResponseDTO>findInvoicesByInvoiceTypesId();
    List<InvoiceResponseDTO> invoiceReportByDate(String reportDate) throws ParseException;
    List<InvoiceResponseDTO> findInvoicesByBranchBranchId(Long branchId);
    List<InvoiceResponseDTO> findInvoicesByCustomerDni(String customerDni);
    List<InvoiceResponseDTO> findInvoicesByUserDni(String dni);

    List<InvoiceResponseDTO> invoiceSell() throws ParseException;
}
