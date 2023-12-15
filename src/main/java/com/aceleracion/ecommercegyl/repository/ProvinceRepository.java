package com.aceleracion.ecommercegyl.repository;

import com.aceleracion.ecommercegyl.model.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinceRepository extends JpaRepository <Province,Long>{
    Province findByProvinceName(String name);

    Province findProvinceByProvinceName(String name);
}
