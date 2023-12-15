package com.aceleracion.ecommercegyl.repository.serviceimpl;

import com.aceleracion.ecommercegyl.dto.mapper.CustomerMapper;
import com.aceleracion.ecommercegyl.dto.request.CustomerRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.CustomerResponseDTO;
import com.aceleracion.ecommercegyl.exception.MyException;
import com.aceleracion.ecommercegyl.model.Address;
import com.aceleracion.ecommercegyl.model.Customer;
import com.aceleracion.ecommercegyl.repository.CustomerRepository;
import com.aceleracion.ecommercegyl.service.service.AddressService;
import com.aceleracion.ecommercegyl.service.service.CustomerService;
import com.aceleracion.ecommercegyl.utils.ListValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final AddressService addressService;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper, AddressService addressService) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.addressService = addressService;
    }

    @Transactional
    @Override
    public CustomerResponseDTO createCustomer(CustomerRequestDTO customerRequestDTO) {

        if (customerRequestDTO == null) {
            throw new MyException("entityNull", "customerRequest");
        }

        if (customerRepository.findCustomerByDni(customerRequestDTO.getDni()) != null) {
            throw new MyException("existDB", "customer");
        }
        Customer objCustomer = customerMapper.reqToCustomer(customerRequestDTO);

        Address objAddress = addressService.createAddress(
                customerRequestDTO.getAddressRequestDTO().getStreet(),
                customerRequestDTO.getAddressRequestDTO().getNumber(),
                customerRequestDTO.getAddressRequestDTO().getCityId()
        );
        objCustomer.setName(objCustomer.getName());
        objCustomer.setLastName(objCustomer.getLastName());
        objCustomer.setPhoneNumber(objCustomer.getPhoneNumber());
        objCustomer.setDni(objCustomer.getDni());
        objCustomer.setEmail(objCustomer.getEmail());
        objCustomer.setBirthdate(objCustomer.getBirthdate());
        objCustomer.setAddress(objAddress);
        objCustomer.setStatus(true);
        customerRepository.save(objCustomer);

        return customerMapper.customerToResp(objCustomer);
    }

    @Override
    public CustomerResponseDTO findCustomerByDni(String dni) {
        if (dni.isBlank()) {
            throw new MyException("entityNull", "dni");
        }
        Customer objCustomer = customerRepository.findCustomerByDniAndStatus(dni, true);
        if (objCustomer == null) {
            throw new MyException("noExistDB", "customer");
        }
        return customerMapper.customerToResp(objCustomer);

    }

    @Override
    public Customer findByDni(String dni) {
        if (dni.isBlank()) {
            throw new MyException("entityNull", "dni");
        }
        Customer objCustomer = customerRepository.findCustomerByDniAndStatus(dni, true);
        if (objCustomer == null) {
            throw new MyException("noExistDB", "customer");
        }
        return objCustomer;

    }

    @Override
    public List<CustomerResponseDTO> findAllByStatus() {
        List<Customer> customers = customerRepository.findAllByStatus(true);
        ListValidator.validateResponseList(customers);

        return customers.stream()
                .map(customerMapper::customerToResp)
                .toList();
    }

    @Override
    public List<CustomerResponseDTO> findAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        ListValidator.validateResponseList(customers);

        return customerMapper.customerToRespList(customers);
    }


    @Override
    public CustomerResponseDTO findCustomerByName(String name, String lastname) {
        if (name.isBlank() || lastname.isBlank()) {
            throw new MyException("entityNull", "name o lastname");
        }
        Customer objCustomer = customerRepository.findCustomerByNameAndLastNameAndStatus(name, lastname, true);
        if (objCustomer == null) {
            throw new MyException("noExistDB", "customer");
        } else {
            return customerMapper.customerToResp(objCustomer);
        }

    }

    @Transactional
    @Override
    public CustomerResponseDTO updateCustomer(String dni, CustomerRequestDTO customerRequestDTO) {
        if (customerRequestDTO == null) {
            throw new MyException("entityNull", "customerRequest");
        }
        if (dni.isBlank()) {
            throw new MyException("entityNull", "dni");
        }

        Customer actualCustomer = customerRepository.findCustomerByDniAndStatus(dni, true);
        if (actualCustomer != null) {
            actualCustomer.setName(customerRequestDTO.getName());
            actualCustomer.setLastName(customerRequestDTO.getLastName());
            actualCustomer.setDni(customerRequestDTO.getDni());
            actualCustomer.setEmail(customerRequestDTO.getEmail());
            actualCustomer.setPhoneNumber(customerRequestDTO.getPhoneNumber());
            actualCustomer.setBirthdate(customerRequestDTO.getBirthdate());
            Address objAddress = addressService.createAddress(
                    customerRequestDTO.getAddressRequestDTO().getStreet(),
                    customerRequestDTO.getAddressRequestDTO().getNumber(),
                    customerRequestDTO.getAddressRequestDTO().getCityId()
            );
            actualCustomer.setAddress(objAddress);
            customerRepository.save(actualCustomer);
            return customerMapper.customerToResp(actualCustomer);
        } else {
            throw new MyException("noExistDB", "customer");
        }
    }


    @Override
    public void changeCustomerStatusByDni(String dni) {
        if (dni.isBlank()) {
            throw new MyException("entityNull", "dni");
        }
        Customer objCustomer = customerRepository.findCustomerByDni(dni);
        if (objCustomer == null) {
            throw new MyException("noExistDB", "customer");
        }
        boolean status = objCustomer.getStatus();
        objCustomer.setStatus(!status);
        customerRepository.save(objCustomer);
    }


}


