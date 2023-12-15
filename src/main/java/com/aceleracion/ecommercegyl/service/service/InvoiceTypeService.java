package com.aceleracion.ecommercegyl.service.service;

import com.aceleracion.ecommercegyl.dto.request.InvoiceTypeRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.InvoiceTypeResponseDTO;
import com.aceleracion.ecommercegyl.model.InvoiceType;


import java.util.List;

public interface InvoiceTypeService {
    InvoiceTypeResponseDTO createInvoiceType(InvoiceTypeRequestDTO invoiceTypeRequestDTO);

    InvoiceTypeResponseDTO findInvoiceTypeById(Long id);

    InvoiceType findById(Long id);

    List<InvoiceTypeResponseDTO> findAllInvoiceTypes();

    List<InvoiceTypeResponseDTO> findAllInvoiceTypesByStatusTrue();

    void changeStatus(Long id);
}
