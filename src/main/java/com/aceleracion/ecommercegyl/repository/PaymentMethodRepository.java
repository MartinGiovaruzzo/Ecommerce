package com.aceleracion.ecommercegyl.repository;

import com.aceleracion.ecommercegyl.model.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod,Long> {

     PaymentMethod findByPaymentMethodName(String name);

     List<PaymentMethod> findAllPaymentMethodByStatusTrue();


}
