package com.aceleracion.ecommercegyl.service.serviceimpl;

import com.aceleracion.ecommercegyl.dto.mapper.CustomerMapper;
import com.aceleracion.ecommercegyl.dto.request.AddressRequestDTO;
import com.aceleracion.ecommercegyl.dto.request.CustomerRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.CustomerResponseDTO;
import com.aceleracion.ecommercegyl.exception.MyException;
import com.aceleracion.ecommercegyl.model.Address;
import com.aceleracion.ecommercegyl.model.City;
import com.aceleracion.ecommercegyl.model.Customer;
import com.aceleracion.ecommercegyl.repository.CustomerRepository;
import com.aceleracion.ecommercegyl.repository.serviceimpl.CustomerServiceImpl;
import com.aceleracion.ecommercegyl.service.service.AddressService;
import com.aceleracion.ecommercegyl.service.service.CustomerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

public class CustomerServiceImplTest {

    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerMapper customerMapper;

    @Mock
    private AddressService addressService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        customerService = new CustomerServiceImpl(customerRepository, customerMapper, addressService);
    }

    @Test
    public void testCreateCustomer_Success() {

        Long cityId = 1L;
        String street = "Street";
        String number = "123";
        CustomerRequestDTO customerRequestDTO = new CustomerRequestDTO();
        customerRequestDTO.setDni("12345678");
        customerRequestDTO.setAddressRequestDTO(new AddressRequestDTO(street, number, cityId));

        City objCity = new City();
        objCity.setCityId(cityId);

        Address objAddress = new Address();
        objAddress.setStreet(street);
        objAddress.setNumber(number);
        objAddress.setCity(objCity);
        objAddress.setStatus(true);

        Customer objCustomer = new Customer();
        objCustomer.setDni(customerRequestDTO.getDni());
        objCustomer.setAddress(objAddress);
        objCustomer.setStatus(true);

        when(addressService.createAddress(street, number, cityId)).thenReturn(objAddress);
        when(customerMapper.reqToCustomer(customerRequestDTO)).thenReturn(objCustomer);
        when(customerRepository.findCustomerByDni(customerRequestDTO.getDni())).thenReturn(null);
        when(customerMapper.customerToResp(objCustomer)).thenReturn(new CustomerResponseDTO());

        CustomerResponseDTO result = customerService.createCustomer(customerRequestDTO);

        Assertions.assertNotNull(result);
        verify(addressService, times(1)).createAddress(street, number, cityId);
        verify(customerMapper, times(1)).reqToCustomer(customerRequestDTO);
        verify(customerRepository, times(1)).findCustomerByDni(customerRequestDTO.getDni());
        verify(customerRepository, times(1)).save(objCustomer);
        verify(customerMapper, times(1)).customerToResp(objCustomer);
    }

    @Test
    public void testCreateCustomer_CustomerExists() {

        CustomerRequestDTO customerRequestDTO = new CustomerRequestDTO();
        customerRequestDTO.setDni("12345678");

        Customer existingCustomer = new Customer();

        when(customerRepository.findCustomerByDni(customerRequestDTO.getDni())).thenReturn(existingCustomer);

        Assertions.assertThrows(MyException.class, () -> customerService.createCustomer(customerRequestDTO));
        verify(customerRepository, times(1)).findCustomerByDni(customerRequestDTO.getDni());
  }

    @Test
    public void testFindByDni_CustomerExists() {
        String dni = "12345678";
        Customer existingCustomer = new Customer();

        when(customerRepository.findCustomerByDniAndStatus(dni, true)).thenReturn(existingCustomer);

        Customer result = customerService.findByDni(dni);

        Assertions.assertEquals(existingCustomer, result);
        verify(customerRepository, times(1)).findCustomerByDniAndStatus(dni, true);
    }

    @Test
    public void testChangeCustomerStatusByDni_CustomerExists_StatusChanged() {

        String dni = "12345678";
        Customer existingCustomer = new Customer();
        existingCustomer.setDni(dni);
        existingCustomer.setStatus(true);

        when(customerRepository.findCustomerByDni(dni)).thenReturn(existingCustomer);

        customerService.changeCustomerStatusByDni(dni);

        verify(customerRepository).save(existingCustomer);
        assertFalse(existingCustomer.getStatus());
    }


}
