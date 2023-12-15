package com.aceleracion.ecommercegyl.dto.mapper;

import com.aceleracion.ecommercegyl.dto.request.ProductRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.ProductResponseDTO;
import com.aceleracion.ecommercegyl.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring",
        uses = {BranchMapper.class, ProductTypeMapper.class, BrandMapper.class})
public interface ProductMapper {

    Product reqToProduct(ProductRequestDTO productRequestDTO);
     @Mapping(source = "productType", target = "productTypeResponseDTO")
    @Mapping(source = "branch", target = "branchResponseDTO")
    @Mapping(source = "brand", target = "brandResponseDTO")
    ProductResponseDTO productToResp(Product product);

    List<ProductResponseDTO> productToRespList(List<Product> products);
}