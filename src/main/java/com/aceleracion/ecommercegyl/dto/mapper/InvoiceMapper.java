package com.aceleracion.ecommercegyl.dto.mapper;

import com.aceleracion.ecommercegyl.dto.request.InvoiceRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.InvoiceResponseDTO;
import com.aceleracion.ecommercegyl.model.Invoice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = {BranchMapper.class, CustomerMapper.class, InvoiceTypeMapper.class, PaymentMethodMapper.class,
                UserMapper.class, DiscountRateMapper.class})
@Component
public interface InvoiceMapper {

    Invoice reqToInvoice(InvoiceRequestDTO invoiceRequestDTO);

    @Mapping(source = "branch", target = "branchResponseDTO")
    @Mapping(source = "customer", target = "customerResponseDTO")
    @Mapping(source = "invoiceType", target = "invoiceTypeResponseDTO")
    @Mapping(source = "paymentMethod", target = "paymentMethodResponseDTO")
    @Mapping(source = "user", target = "userResponseDTO")
    @Mapping(source = "discountRate", target = "discountRateResponseDTO")
    InvoiceResponseDTO invoiceToResp(Invoice invoice);

    List<InvoiceResponseDTO> invoiceToRespList(List<Invoice> invoices);
}
