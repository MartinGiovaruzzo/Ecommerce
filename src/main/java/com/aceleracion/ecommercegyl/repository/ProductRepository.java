package com.aceleracion.ecommercegyl.repository;

import com.aceleracion.ecommercegyl.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByProductCode(Long productCode);

    Product findByProductCodeAndStatusTrue(Long productCode);

    List<Product> findAllByStatusTrue();

    List<Product> findAllByBranchBranchIdAndStatusTrue(Long branchId);

    List<Product> findAllByBranchBranchIdAndProductTypeProductTypeIdAndStatusTrue(Long branchId, Long productTypeId);

    List<Product> findAllByBranchBranchIdAndModelAndStatusTrue(Long branchId, String model);

}
