package com.aceleracion.ecommercegyl.dto.mapper;

import com.aceleracion.ecommercegyl.dto.request.ProductTypeRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.ProductTypeResponseDTO;
import com.aceleracion.ecommercegyl.model.ProductType;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface ProductTypeMapper {

    ProductType reqToProductType(ProductTypeRequestDTO productTypeRequestDTO);

    ProductTypeResponseDTO productTypeToResp(ProductType productType);

    List<ProductTypeResponseDTO> productTypeToRespList(List<ProductType> productTypes);
}
