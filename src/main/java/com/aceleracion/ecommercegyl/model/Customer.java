package com.aceleracion.ecommercegyl.model;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
public class Customer extends Person {

    public Customer() {
        super();
    }

    public Customer(Long personId, String name, String lastName, String dni, String email, String phoneNumber, LocalDate birthdate, Boolean status, Address address) {
        super(personId, name, lastName, dni, email, phoneNumber, birthdate, status, address);
    }
}


