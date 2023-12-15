package com.aceleracion.ecommercegyl.dto.mapper;

import com.aceleracion.ecommercegyl.dto.request.BackSaleRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.BackSaleResponseDTO;
import com.aceleracion.ecommercegyl.model.BackSale;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = InvoiceMapper.class)
@Component
public interface BackSaleMapper {

    BackSale reqToBackSale(BackSaleRequestDTO backSaleRequestDTO);

    @Mapping(source = "invoiceBackSale", target = "invoiceBackSaleResponseDTO")
    @Mapping(source = "invoiceSale", target = "invoiceSaleResponseDTO")
    BackSaleResponseDTO backSaleToResp(BackSale backSale);

    List<BackSaleResponseDTO> backSaleToRespList(List<BackSale> backSales);
}
