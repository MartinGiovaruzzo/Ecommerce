package com.aceleracion.ecommercegyl.repository;
import com.aceleracion.ecommercegyl.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand,Long> {

    Brand findByName(String name);

    List<Brand> findAllBrandByStatusTrue();
}
