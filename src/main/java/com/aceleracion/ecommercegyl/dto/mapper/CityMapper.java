package com.aceleracion.ecommercegyl.dto.mapper;

import com.aceleracion.ecommercegyl.dto.request.CityRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.CityResponseDTO;
import com.aceleracion.ecommercegyl.exception.MyException;
import com.aceleracion.ecommercegyl.model.City;
import com.aceleracion.ecommercegyl.model.Province;
import com.aceleracion.ecommercegyl.repository.ProvinceRepository;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Optional;

@Component
@Mapper(componentModel = "spring",
uses = ProvinceMapper.class)
public interface CityMapper {
    @Mapping(target = "province", source = "provinceId", qualifiedByName = "getProvinceById")
    City reqToCity(CityRequestDTO cityRequestDTO, @Context ProvinceRepository provinceRepository);

    @Mapping(source = "province", target = "provinceResponseDTO")
    CityResponseDTO  cityToResp(City city);

    @Named("getProvinceById")
    default Province getProvinceById(Long provinceId, @Context ProvinceRepository provinceRepository) throws MyException{
        Optional<Province> optionalProvince=provinceRepository.findById(provinceId);
        if (optionalProvince.isPresent()){
            return optionalProvince.get();
        }else {
            throw  new MyException("noExistDB", "province");
        }

    }

    List<CityResponseDTO> cityToRespList(List<City> cities);
}
