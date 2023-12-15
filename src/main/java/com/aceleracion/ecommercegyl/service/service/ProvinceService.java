package com.aceleracion.ecommercegyl.service.service;

import com.aceleracion.ecommercegyl.dto.request.ProvinceRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.ProvinceResponseDTO;
import com.aceleracion.ecommercegyl.model.Province;
import java.util.List;


public interface ProvinceService {
    ProvinceResponseDTO createProvince(ProvinceRequestDTO provinceRequestDto) ;

    ProvinceResponseDTO findProvinceById(Long id);

    Province findById(Long provinceId);

    void changeStatus(Long provinceId);

    List<ProvinceResponseDTO> findAllProvinces();
}
