package com.aceleracion.ecommercegyl.dto.mapper;

import com.aceleracion.ecommercegyl.dto.request.InvoiceTypeRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.InvoiceTypeResponseDTO;
import com.aceleracion.ecommercegyl.model.InvoiceType;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface InvoiceTypeMapper {
    InvoiceType reqToInvType(InvoiceTypeRequestDTO invoiceTypeRequestDTO);

    InvoiceTypeResponseDTO invTypeToResp(InvoiceType invoiceType);

    List<InvoiceTypeResponseDTO> invTypeToRespList(List<InvoiceType> invoiceTypes);
}
