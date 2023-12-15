package com.aceleracion.ecommercegyl.dto.mapper;

import com.aceleracion.ecommercegyl.dto.request.ProvinceRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.ProvinceResponseDTO;
import com.aceleracion.ecommercegyl.model.Province;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface ProvinceMapper {
    Province reqToProvince(ProvinceRequestDTO provinceRequestDTO);
    ProvinceResponseDTO provinceToResp(Province province);

    List<ProvinceResponseDTO> provinceToRespList(List<Province> provinces);
}
