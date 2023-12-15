package com.aceleracion.ecommercegyl.repository;

import com.aceleracion.ecommercegyl.model.DiscountRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscountRateRepository extends JpaRepository<DiscountRate,Long> {
    DiscountRate findByDiscountName(String name);

    List<DiscountRate> findAllByStatusTrue();

    List<DiscountRate> findAll();

}
