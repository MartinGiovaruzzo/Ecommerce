package com.aceleracion.ecommercegyl.repository;

import com.aceleracion.ecommercegyl.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository <City,Long>{
    City findCityByCityName(String cityMame);

    List<City> findAllByStatusTrue();
}
