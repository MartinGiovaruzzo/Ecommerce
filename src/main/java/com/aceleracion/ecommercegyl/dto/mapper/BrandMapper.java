package com.aceleracion.ecommercegyl.dto.mapper;

import com.aceleracion.ecommercegyl.dto.request.BrandRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.BrandResponseDTO;
import com.aceleracion.ecommercegyl.model.Brand;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Mapper(componentModel = "spring")
public interface BrandMapper {

    Brand reqToBrand(BrandRequestDTO brandRequestDTO);
    BrandResponseDTO brandToResp(Brand brand);

    List<BrandResponseDTO> brandToRespList(List<Brand> brands);
}
