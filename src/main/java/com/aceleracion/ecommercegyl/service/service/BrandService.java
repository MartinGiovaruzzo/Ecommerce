package com.aceleracion.ecommercegyl.service.service;

import com.aceleracion.ecommercegyl.dto.request.BrandRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.BrandResponseDTO;
import com.aceleracion.ecommercegyl.model.Brand;


import java.util.List;

public interface BrandService {

    BrandResponseDTO createBrand(BrandRequestDTO brandRequestDTO) ;

    BrandResponseDTO updateBrand(String name, BrandRequestDTO brandRequestDTO) ;

    BrandResponseDTO findBrandByName(BrandRequestDTO brandNameRequestDTO);

    Brand findBrandById(Long id);

    List<BrandResponseDTO> findAllBrands();

    void changeStatus(String name);

    List<BrandResponseDTO> findAllBrandByStatusTrue();
}
