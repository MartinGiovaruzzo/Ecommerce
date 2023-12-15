package com.aceleracion.ecommercegyl.dto.mapper;


import com.aceleracion.ecommercegyl.dto.response.AddressResponseDTO;
import com.aceleracion.ecommercegyl.model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring",
uses = CityMapper.class)
@Component
public interface AddressMapper {

    @Mapping(source = "city", target = "cityResponseDTO")
    AddressResponseDTO addressToResp(Address address);

}
