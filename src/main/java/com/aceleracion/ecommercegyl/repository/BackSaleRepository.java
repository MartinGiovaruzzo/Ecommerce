package com.aceleracion.ecommercegyl.repository;

import com.aceleracion.ecommercegyl.model.BackSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BackSaleRepository  extends JpaRepository<BackSale,Long> {

}
