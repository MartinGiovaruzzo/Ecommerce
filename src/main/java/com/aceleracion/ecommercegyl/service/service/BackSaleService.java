package com.aceleracion.ecommercegyl.service.service;

import com.aceleracion.ecommercegyl.dto.request.BackSaleRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.BackSaleResponseDTO;


import java.util.List;

public interface BackSaleService {

    BackSaleResponseDTO createBackSale(BackSaleRequestDTO backSaleRequestDTO);
    BackSaleResponseDTO findBackSaleById(Long backSaleId);
    List<BackSaleResponseDTO> findAllBackSales();

}
