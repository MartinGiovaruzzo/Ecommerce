package com.aceleracion.ecommercegyl.repository.serviceimpl;

import com.aceleracion.ecommercegyl.dto.mapper.ProvinceMapper;
import com.aceleracion.ecommercegyl.dto.request.ProvinceRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.ProvinceResponseDTO;
import com.aceleracion.ecommercegyl.exception.MyException;
import com.aceleracion.ecommercegyl.model.Province;
import com.aceleracion.ecommercegyl.repository.ProvinceRepository;
import com.aceleracion.ecommercegyl.service.service.ProvinceService;
import com.aceleracion.ecommercegyl.utils.ListValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProvinceServiceImpl implements ProvinceService {
    private final ProvinceRepository provinceRepository;
    private final ProvinceMapper provinceMapper;

    public ProvinceServiceImpl(ProvinceRepository provinceRepository, ProvinceMapper provinceMapper) {
        this.provinceRepository = provinceRepository;
        this.provinceMapper = provinceMapper;
    }

    @Transactional
    @Override
    public ProvinceResponseDTO createProvince(ProvinceRequestDTO provinceRequestDTO) throws MyException {
        if (provinceRequestDTO != null) {
            Province objProvince = provinceMapper.reqToProvince(provinceRequestDTO);
            if (provinceRepository.findByProvinceName(objProvince.getProvinceName()) != null) {
                throw new MyException("existDB", "province");
            }
            provinceRepository.save(objProvince);
            return provinceMapper.provinceToResp(objProvince);
        } else {
            throw new MyException("entityNull", "provinceRequest");
        }
    }

    @Override
    public ProvinceResponseDTO findProvinceById(Long provinceId) {
        if (provinceId == null) {
            throw new MyException("entityNull", "provinceId");
        }
        Optional<Province> objProvince = provinceRepository.findById(provinceId);
        if (objProvince.isPresent()) {
            return provinceMapper.provinceToResp(objProvince.get());
        } else {
            throw new MyException("noExistDB", "province");
        }
    }

    @Override
    public Province findById(Long provinceId) {
        if (provinceId == null) {
            throw new MyException("entityNull", "provinceId");
        }
        Optional<Province> objProvince = provinceRepository.findById(provinceId);
        if (objProvince.isPresent()) {
            return objProvince.get();
        } else {
            throw new MyException("noExistDB", "province");
        }
    }

    @Override
    public void changeStatus(Long provinceId) {
        if (provinceId == null) {
            throw new MyException("entityNull", "provinceId");
        }
        Optional<Province> optional = provinceRepository.findById(provinceId);
        if (optional.isEmpty()) {

            throw new MyException("existDBStatus", "province");
        }

        Province objProvince = optional.get();

        boolean status = objProvince.getStatus();

        objProvince.setStatus(!status);

        provinceRepository.save(objProvince);
    }

    @Override
    public List<ProvinceResponseDTO> findAllProvinces() {
        List<Province> provinces = provinceRepository.findAll();
        ListValidator.validateResponseList(provinces);

        return provinceMapper.provinceToRespList(provinces);
    }

}
