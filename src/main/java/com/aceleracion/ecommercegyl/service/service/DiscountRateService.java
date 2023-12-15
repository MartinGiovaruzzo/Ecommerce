package com.aceleracion.ecommercegyl.service.service;

import com.aceleracion.ecommercegyl.dto.request.DiscountRateRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.DiscountRateResponseDTO;
import com.aceleracion.ecommercegyl.model.DiscountRate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DiscountRateService {

    DiscountRateResponseDTO createDiscountRate(DiscountRateRequestDTO discountRateRequestDTO);

    DiscountRateResponseDTO findDiscountRateById(Long id);

    DiscountRate findById(Long id);

    void changeStatus(Long id);

    List<DiscountRateResponseDTO> findAllDiscountRatesByStatusTrue();

    List<DiscountRateResponseDTO> findAll();
}
