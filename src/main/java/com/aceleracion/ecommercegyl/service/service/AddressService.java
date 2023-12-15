package com.aceleracion.ecommercegyl.service.service;

import com.aceleracion.ecommercegyl.dto.request.AddressRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.AddressResponseDTO;
import com.aceleracion.ecommercegyl.model.Address;


public interface AddressService {

    Address createAddress(String street, String number, Long cityId) ;

    AddressResponseDTO findAddressByName(AddressRequestDTO addressRequestDTO);

    AddressResponseDTO findAddressById(Long idAddress);

    void changeStatus(Long idAddress);
}
