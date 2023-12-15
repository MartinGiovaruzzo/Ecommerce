package com.aceleracion.ecommercegyl.repository;


import com.aceleracion.ecommercegyl.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Customer findCustomerByDni(String dni);
    Customer findCustomerByDniAndStatus( String dni, Boolean status);
    List<Customer> findAllByStatus(Boolean status);
    Customer findCustomerByNameAndLastNameAndStatus(String name, String lastName, Boolean status);


}
