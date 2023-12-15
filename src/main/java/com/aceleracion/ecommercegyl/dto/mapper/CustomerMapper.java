package com.aceleracion.ecommercegyl.dto.mapper;

import com.aceleracion.ecommercegyl.dto.request.CustomerRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.CustomerResponseDTO;
import com.aceleracion.ecommercegyl.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = AddressMapper.class)
@Component
public interface CustomerMapper {

    Customer reqToCustomer(CustomerRequestDTO customerRequestDTO);

    @Mapping(source = "address", target = "addressResponseDTO")
    CustomerResponseDTO customerToResp(Customer customer);

    List<CustomerResponseDTO> customerToRespList(List<Customer> customers);
}
