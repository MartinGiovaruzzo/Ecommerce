package com.aceleracion.ecommercegyl.service.service;

import com.aceleracion.ecommercegyl.dto.request.CityRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.CityResponseDTO;
import com.aceleracion.ecommercegyl.model.City;

import java.util.List;

public interface CityService {
    CityResponseDTO createCity(CityRequestDTO cityRequestDTO) ;

    City findCityById(Long cityId);

    CityResponseDTO findCityRspById(Long id);

    List<CityResponseDTO> findAllCities();

    List<CityResponseDTO> findAllCitiesByStatusTrue();

    void changeCityStatus(Long cityId);
}

