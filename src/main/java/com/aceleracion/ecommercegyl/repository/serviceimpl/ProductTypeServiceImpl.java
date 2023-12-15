package com.aceleracion.ecommercegyl.repository.serviceimpl;

import com.aceleracion.ecommercegyl.dto.mapper.ProductTypeMapper;
import com.aceleracion.ecommercegyl.dto.request.ProductTypeRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.ProductTypeResponseDTO;
import com.aceleracion.ecommercegyl.exception.MyException;
import com.aceleracion.ecommercegyl.model.ProductType;
import com.aceleracion.ecommercegyl.repository.ProductTypeRepository;
import com.aceleracion.ecommercegyl.service.service.ProductTypeService;
import com.aceleracion.ecommercegyl.utils.ListValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductTypeServiceImpl implements ProductTypeService {
    private final ProductTypeMapper productTypeMapper;
    private final ProductTypeRepository productTypeRepository;

    public ProductTypeServiceImpl(ProductTypeMapper productTypeMapper, ProductTypeRepository productTypeRepository) {
        this.productTypeMapper = productTypeMapper;
        this.productTypeRepository = productTypeRepository;
    }

    @Transactional
    @Override
    public ProductTypeResponseDTO createProductType(ProductTypeRequestDTO productTypeRequestDTO) {
        if (productTypeRequestDTO != null) {
            ProductType objActualProductType = productTypeRepository
                    .findByProductType(productTypeRequestDTO.getProductType());

            if (objActualProductType != null) {
                Boolean status = objActualProductType.getStatus();
                if (Boolean.TRUE.equals(status)) {
                    throw new MyException("existDB", "productType");
                } else {
                    throw new MyException("existDBStatus", "productType");
                }
            }
            ProductType objProductType = productTypeMapper.reqToProductType(productTypeRequestDTO);
            objProductType.setStatus(true);
            productTypeRepository.save(objProductType);
            return productTypeMapper.productTypeToResp(objProductType);
        } else {
            throw new MyException("entityNull", "productTypeRequest");
        }
    }

    @Override
    public ProductTypeResponseDTO findProductTypeById(Long productTypeId) {
        if (productTypeId == null) {
            throw new MyException("entityNull", "productTypeId");
        }
        ProductType objProductType = findById(productTypeId);
        return productTypeMapper.productTypeToResp(objProductType);
    }

    @Override
    public ProductTypeResponseDTO findProductTypeByName(String productType) {
        if (productType.isBlank()) {
            throw new MyException("entityNull", "productType");
        }
        ProductType objProductType = productTypeRepository.findByProductType(productType);

        if (objProductType == null) {
            throw new MyException("noExistDB", "productType");
        }
        return productTypeMapper.productTypeToResp(objProductType);
    }

    @Override
    public List<ProductTypeResponseDTO> findAllProductTypes() {
        List<ProductType> productTypes = productTypeRepository.findAll();
        ListValidator.validateResponseList(productTypes);

        return productTypeMapper.productTypeToRespList(productTypes);
    }


    @Override
    public ProductType findById(Long productTypeId) {
        if (productTypeId == null) {
            throw new MyException("entityNull", "productTypeId");
        }
        return productTypeRepository.findById(productTypeId)
                .orElseThrow(() -> new MyException("noExistDB", "productType"));
    }

    @Override
    public void changeStatus(Long productTypeId) {
        if (productTypeId == null) {
            throw new MyException("entityNull", "productTypeId");
        }
        Optional<ProductType> optionalProductType = productTypeRepository.findById(productTypeId);

        if (optionalProductType.isPresent()) {

            ProductType productType = optionalProductType.get();
            boolean status = productType.getStatus();

            productType.setStatus(!status);

            productTypeRepository.save(productType);
        } else {
            throw new MyException("existDBStatus", "productType");
        }

    }

    @Override
    public List<ProductTypeResponseDTO> findAllProductTypeByStatusTrue() {
        List<ProductType> productTypesStatusTrue = productTypeRepository.findAllProductTypeByStatusTrue();
        ListValidator.validateResponseList(productTypesStatusTrue);

        return productTypeMapper.productTypeToRespList(productTypesStatusTrue);
    }
}
