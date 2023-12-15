package com.aceleracion.ecommercegyl.repository.serviceimpl;

import com.aceleracion.ecommercegyl.dto.mapper.BrandMapper;
import com.aceleracion.ecommercegyl.dto.request.BrandRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.BrandResponseDTO;
import com.aceleracion.ecommercegyl.exception.MyException;
import com.aceleracion.ecommercegyl.model.Brand;
import com.aceleracion.ecommercegyl.repository.BrandRepository;
import com.aceleracion.ecommercegyl.service.service.BrandService;
import com.aceleracion.ecommercegyl.utils.ListValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;

    public BrandServiceImpl(BrandRepository brandRepository, BrandMapper brandMapper) {
        this.brandRepository = brandRepository;
        this.brandMapper = brandMapper;
    }

    @Transactional
    @Override
    public BrandResponseDTO createBrand(BrandRequestDTO brandRequestDTO) throws MyException {
        if (brandRequestDTO != null) {
            Brand objBrand = brandMapper.reqToBrand(brandRequestDTO);
            if (brandRepository.findByName(objBrand.getName()) != null) {
                throw new MyException("existDB", "brand");
            }
            objBrand.setStatus(true);
            brandRepository.save(objBrand);

            return brandMapper.brandToResp(objBrand);
        } else {
            throw new MyException("entityNull", "brandRequest");
        }
    }

    @Transactional
    @Override
    public BrandResponseDTO updateBrand(String name, BrandRequestDTO brandRequestDTO) throws MyException {
        if (name == null) {
            throw new MyException("entityNull", "name");
        }

        Brand objBrand = brandRepository.findByName(name);

        if (objBrand != null) {
            objBrand.setName(brandRequestDTO.getName());
            brandRepository.save(objBrand);
            return brandMapper.brandToResp(objBrand);
        } else {
            throw new MyException("noExistDB", "brand");
        }
    }

    @Override
    public BrandResponseDTO findBrandByName(BrandRequestDTO brandNameRequestDTO) {
        Brand objBrand = brandRepository.findByName(brandNameRequestDTO.getName());

        if (objBrand == null) {
            throw new MyException("noExistDB", "brand");
        }
        return brandMapper.brandToResp(objBrand);
    }


    @Override
    public Brand findBrandById(Long brandId) {
        if (brandId == null) {
            throw new MyException("entityNull", "brandId");
        }
        Optional<Brand> objBrand = brandRepository.findById(brandId);
        if (objBrand.isPresent()) {
            return objBrand.get();
        }
        throw new MyException("noExistDB", "brand");
    }

    @Override
    public List<BrandResponseDTO> findAllBrands() {
        List<Brand> brands = brandRepository.findAll();
        ListValidator.validateResponseList(brands);

        return brandMapper.brandToRespList(brands);
    }


    @Override
    public void changeStatus(String name) {
        if (name == null) {
            throw new MyException("entityNull", "name");
        }
        Brand objBrand = brandRepository.findByName(name);
        if (objBrand == null) {
            throw new MyException("noExistDB", "brand");
        }
        boolean status = objBrand.getStatus();

        objBrand.setStatus(!status);

        brandRepository.save(objBrand);
    }

    @Override
    public List<BrandResponseDTO> findAllBrandByStatusTrue() {
        List<Brand> brandsStatusTrue = brandRepository.findAllBrandByStatusTrue();
        ListValidator.validateResponseList(brandsStatusTrue);

        return brandMapper.brandToRespList(brandsStatusTrue);
    }

}
