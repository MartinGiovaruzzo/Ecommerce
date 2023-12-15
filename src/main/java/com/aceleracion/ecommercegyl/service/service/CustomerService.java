package com.aceleracion.ecommercegyl.service.service;

import com.aceleracion.ecommercegyl.dto.request.CustomerRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.CustomerResponseDTO;
import com.aceleracion.ecommercegyl.model.Customer;
import java.util.List;

public interface CustomerService {
    CustomerResponseDTO createCustomer(CustomerRequestDTO customerDto);
    CustomerResponseDTO findCustomerByDni(String dni);
    Customer findByDni(String dni);
    List<CustomerResponseDTO> findAllByStatus();
    List<CustomerResponseDTO> findAllCustomers();
    CustomerResponseDTO findCustomerByName(String name, String lastname);
    CustomerResponseDTO updateCustomer(String dni, CustomerRequestDTO customerRequestDTO) ;
    void changeCustomerStatusByDni(String dni) ;


}
