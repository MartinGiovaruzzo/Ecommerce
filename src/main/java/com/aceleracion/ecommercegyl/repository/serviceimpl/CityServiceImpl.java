package com.aceleracion.ecommercegyl.repository.serviceimpl;

import com.aceleracion.ecommercegyl.dto.mapper.CityMapper;
import com.aceleracion.ecommercegyl.dto.request.CityRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.CityResponseDTO;
import com.aceleracion.ecommercegyl.exception.MyException;
import com.aceleracion.ecommercegyl.model.City;
import com.aceleracion.ecommercegyl.repository.CityRepository;
import com.aceleracion.ecommercegyl.repository.ProvinceRepository;
import com.aceleracion.ecommercegyl.service.service.CityService;
import com.aceleracion.ecommercegyl.utils.ListValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    private final ProvinceRepository provinceRepository;

    public CityServiceImpl(CityRepository cityRepository, CityMapper cityMapper, ProvinceRepository provinceRepository) {
        this.cityRepository = cityRepository;
        this.cityMapper = cityMapper;
        this.provinceRepository = provinceRepository;
    }

    @Transactional
    @Override
    public CityResponseDTO createCity(CityRequestDTO cityRequestDTO) {

        if (cityRequestDTO != null) {
            City objCity = cityMapper.reqToCity(cityRequestDTO, provinceRepository);
            City objActualCity = cityRepository.findCityByCityName(objCity.getCityName());

            if (objActualCity != null) {
                Boolean status = objActualCity.getStatus();
                if (status != null) {
                    if (!status.booleanValue()) {
                        throw new MyException("existDBStatus", "city");
                    }
                } else {
                    // Manejar el caso en que getStatus() sea nulo
                    throw new MyException("entityNull", "city");
                }
                throw new MyException("existDB", "city");
            }
            objCity.setStatus(true);
            cityRepository.save(objCity);
            return cityMapper.cityToResp(objCity);
        } else {
            throw new MyException("entityNull", "cityRequest");
        }
    }

    @Override
    public City findCityById(Long cityId) {
        if (cityId == null) {
            throw new MyException("entityNull", "cityId");
        }
        Optional<City> objCity = cityRepository.findById(cityId);
        if (objCity.isPresent()) {
            return objCity.get();
        } else {
            throw new MyException("noExistDB", "city");
        }
    }

    @Override
    public CityResponseDTO findCityRspById(Long cityId) {
        if (cityId == null) {
            throw new MyException("entityNull", "cityId");
        }
        Optional<City> objCity = cityRepository.findById(cityId);

        if (objCity.isPresent()) {
            return cityMapper.cityToResp(objCity.get());
        } else {
            throw new MyException("noExistDB", "city");
        }
    }

    @Override
    public List<CityResponseDTO> findAllCities() {
        List<City> cities = cityRepository.findAll();
        ListValidator.validateResponseList(cities);

        return cityMapper.cityToRespList(cities);
    }

    @Override
    public List<CityResponseDTO> findAllCitiesByStatusTrue() {
        List<City> cities = cityRepository.findAllByStatusTrue();
        ListValidator.validateResponseList(cities);

        return cityMapper.cityToRespList(cities);
    }

    @Override
    public void changeCityStatus(Long cityId) {
        if (cityId == null) {
            throw new MyException("entityNull", "cityId");
        }
        City objCity = cityRepository.findById(cityId).orElseThrow(() -> new MyException("noExistDB", "city"));
        objCity.setStatus(!objCity.getStatus());
        cityRepository.save(objCity);
    }
}



