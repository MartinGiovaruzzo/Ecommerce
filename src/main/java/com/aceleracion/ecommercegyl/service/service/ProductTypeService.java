package com.aceleracion.ecommercegyl.service.service;

import com.aceleracion.ecommercegyl.dto.request.ProductTypeRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.ProductTypeResponseDTO;
import com.aceleracion.ecommercegyl.model.ProductType;

import java.util.List;

public interface ProductTypeService {
    ProductTypeResponseDTO createProductType(ProductTypeRequestDTO productTypeRequestDTO) ;

    ProductTypeResponseDTO findProductTypeById(Long productTypeId);

    ProductType findById(Long productTypeId);

    ProductTypeResponseDTO findProductTypeByName(String productType);

    List<ProductTypeResponseDTO> findAllProductTypes();

    void changeStatus(Long id);

    List<ProductTypeResponseDTO> findAllProductTypeByStatusTrue();
}
