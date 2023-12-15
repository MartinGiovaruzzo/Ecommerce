package com.aceleracion.ecommercegyl.dto.mapper;

import com.aceleracion.ecommercegyl.dto.request.DiscountRateRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.DiscountRateResponseDTO;
import com.aceleracion.ecommercegyl.model.DiscountRate;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface DiscountRateMapper {
    DiscountRate reqToDiscountRate(DiscountRateRequestDTO discountRateRequestDTO);
    DiscountRateResponseDTO discountRateToResp(DiscountRate discountRate);
    List<DiscountRateResponseDTO> discountRatesListToRespList(List<DiscountRate> discountRates);
}
