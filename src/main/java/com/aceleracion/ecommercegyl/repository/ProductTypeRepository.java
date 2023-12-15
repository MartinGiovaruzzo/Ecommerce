package com.aceleracion.ecommercegyl.repository;

import com.aceleracion.ecommercegyl.model.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {

    ProductType findByProductTypeId(Long id);

    ProductType findByProductType(String productType);
    List<ProductType> findAllProductTypeByStatusTrue();
}
