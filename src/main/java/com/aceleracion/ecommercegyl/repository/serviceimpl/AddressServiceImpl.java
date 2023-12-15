package com.aceleracion.ecommercegyl.repository.serviceimpl;

import com.aceleracion.ecommercegyl.dto.mapper.AddressMapper;
import com.aceleracion.ecommercegyl.dto.request.AddressRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.AddressResponseDTO;
import com.aceleracion.ecommercegyl.exception.MyException;
import com.aceleracion.ecommercegyl.model.Address;
import com.aceleracion.ecommercegyl.model.City;
import com.aceleracion.ecommercegyl.repository.AddressRepository;
import com.aceleracion.ecommercegyl.service.service.AddressService;
import com.aceleracion.ecommercegyl.service.service.CityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;
    private final CityService cityService;

    public AddressServiceImpl(AddressRepository addressRepository, AddressMapper addressMapper, CityService cityService) {
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
        this.cityService = cityService;
    }

    @Transactional
    @Override
    public Address createAddress(String street, String number, Long cityId) {
        if (street.isEmpty() || number.isEmpty() || cityId == null) {
            throw new MyException("entityNull", "street, number o cityId");
        }

        Address objAddress = new Address();
        objAddress.setStreet(street);
        objAddress.setNumber(number);
        City objCity = cityService.findCityById(cityId);
        objAddress.setCity(objCity);
        objAddress.setStatus(true);
        addressRepository.save(objAddress);
        return objAddress;

    }

    @Override
    public AddressResponseDTO findAddressByName(AddressRequestDTO addressRequestDTO) {
        Address objAddress = addressRepository.findAddressByStreetAndNumber(addressRequestDTO.getStreet(), addressRequestDTO.getNumber());
        if (objAddress == null) {
            throw new MyException("noExistDB", "address");
        }
        return addressMapper.addressToResp(objAddress);
    }

    @Override
    public AddressResponseDTO findAddressById(Long addressId) {
        if (addressId == null) {
            throw new MyException("entityNull", "addressId");
        }
        Optional<Address> objAddress = addressRepository.findById(addressId);
        if (objAddress.isEmpty()) {
            throw new MyException("noExistDB", "address");
        }
        AddressResponseDTO addressResponseDTO = addressMapper.addressToResp(objAddress.get());
        addressResponseDTO.setCityResponseDTO(cityService.findCityRspById(objAddress.get().getAddressId()));

        return addressResponseDTO;
    }

    @Override
    public void changeStatus(Long addressId) {
        if (addressId == null) {
            throw new MyException("entityNull", "addressId");
        }
        Optional<Address> optionalAddress = addressRepository.findById(addressId);

        if (optionalAddress.isPresent()) {
            Address objAddress = optionalAddress.get();
            boolean status = objAddress.getStatus();

            objAddress.setStatus(!status);

            addressRepository.save(objAddress);
        } else {
            throw new MyException("noExistDB", "address");
        }

    }

}

