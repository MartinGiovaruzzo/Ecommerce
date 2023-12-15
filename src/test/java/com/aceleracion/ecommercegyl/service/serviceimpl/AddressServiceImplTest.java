package com.aceleracion.ecommercegyl.service.serviceimpl;

import com.aceleracion.ecommercegyl.dto.mapper.AddressMapper;
import com.aceleracion.ecommercegyl.dto.request.AddressRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.AddressResponseDTO;
import com.aceleracion.ecommercegyl.exception.MyException;
import com.aceleracion.ecommercegyl.model.Address;
import com.aceleracion.ecommercegyl.model.City;
import com.aceleracion.ecommercegyl.repository.AddressRepository;
import com.aceleracion.ecommercegyl.repository.serviceimpl.AddressServiceImpl;
import com.aceleracion.ecommercegyl.repository.serviceimpl.CityServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class AddressServiceImplTest {
    @InjectMocks
    private AddressServiceImpl addressService;
    @Mock
    private  AddressRepository addressRepository;
    @Mock
    AddressMapper addressMapper;
    @Mock
    CityServiceImpl cityService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        addressService=new AddressServiceImpl(addressRepository,addressMapper,cityService);
    }

    @Test
    void createAddress() {

        String street = "Example Street";
        String number = "123";
        Long cityId = 1L;

        City mockCity=new City();
        mockCity.setCityId(cityId);

        Address objAddress=new Address();
        objAddress.setStreet(street);
        objAddress.setNumber(number);
        objAddress.setCity(mockCity);
        objAddress.setStatus(true);

        when(cityService.findCityById(cityId)).thenReturn(mockCity);
        when(addressRepository.save(any(Address.class))).thenReturn(objAddress);

        Address result = addressService.createAddress(street, number, cityId);

        assertNotNull(result);
        assertEquals(street,result.getStreet());
        assertEquals(number, result.getNumber());
        assertEquals(mockCity, result.getCity());
        assertTrue(result.getStatus());
        verify(cityService,times(1)).findCityById(cityId);
        verify(addressRepository, times(1)).save(any(Address.class));

    }

    @Test
    void findAddressByName() {
        AddressRequestDTO addressRequestDTO= new AddressRequestDTO();
        addressRequestDTO.setStreet("Av Olaya");
        addressRequestDTO.setNumber("18");

        Address objAddress= new Address();
        objAddress.setAddressId(1L);
        objAddress.setStreet("Av Olaya");
        objAddress.setNumber("18");

        AddressResponseDTO expectedResponseDTO =new AddressResponseDTO();
        expectedResponseDTO.setStreet("Av Olaya");
        expectedResponseDTO.setNumber("18");
        when(addressRepository.findAddressByStreetAndNumber(anyString(),anyString())).thenReturn(objAddress);
        when(addressMapper.addressToResp(objAddress)).thenReturn(expectedResponseDTO);

        AddressResponseDTO actualResponse=addressService.findAddressByName(addressRequestDTO);
        assertNotNull(actualResponse);
        assertEquals(expectedResponseDTO,actualResponse);
       verify(addressRepository,times(1)).findAddressByStreetAndNumber("Av Olaya","18");
       verify(addressMapper,times(1)).addressToResp(objAddress);

    }

    @Test
    void findAddressById() {
            try  {
                when(addressRepository.findById(2L)).thenReturn(Optional.empty());
                assertThrows(MyException.class, () -> addressService.findAddressById(2L));
                verify(addressRepository, times(1)).findById(2L);

             }catch (MyException e){
                System.out.println(e.getMessage());
                     }

    }

    @Test
    void changeStatus() {
        Long addressId=1L;
        Address objAddress=new Address();
        objAddress.setAddressId(addressId);
        objAddress.setStatus(true);

        Optional<Address> optionalAddress= Optional.of(objAddress);
        when(addressRepository.findById(addressId)).thenReturn(optionalAddress);
        addressService.changeStatus(addressId);
        assertFalse(objAddress.getStatus());
        verify(addressRepository,times(1)).save(objAddress);
    }
}