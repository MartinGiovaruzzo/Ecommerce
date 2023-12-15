package com.aceleracion.ecommercegyl.repository;

import com.aceleracion.ecommercegyl.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    Address findAddressByStreetAndNumber(String street, String number);

}
