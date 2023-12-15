package com.aceleracion.ecommercegyl.service.serviceimpl;

import com.aceleracion.ecommercegyl.dto.mapper.ProductTypeMapper;
import com.aceleracion.ecommercegyl.dto.request.ProductTypeRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.ProductTypeResponseDTO;
import com.aceleracion.ecommercegyl.exception.MyException;
import com.aceleracion.ecommercegyl.model.ProductType;
import com.aceleracion.ecommercegyl.repository.ProductTypeRepository;
import com.aceleracion.ecommercegyl.repository.serviceimpl.ProductTypeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductTypeServiceImplTest {

    @Mock
    private ProductTypeMapper productTypeMapper;

    @Mock
    private ProductTypeRepository productTypeRepository;

    @InjectMocks
    private ProductTypeServiceImpl productTypeService;

    @Test
    public void testCreateProductType_ValidRequest_ReturnsResponseDTO() {
        ProductTypeRequestDTO productTypeRequestDTO = new ProductTypeRequestDTO();
        productTypeRequestDTO.setProductType("Product Type 1");

        ProductType objActualProductType = new ProductType();
        objActualProductType.setProductType("Product Type 1");
        objActualProductType.setStatus(false);

        when(productTypeRepository.findByProductType("Product Type 1")).thenReturn(objActualProductType);

        MyException exception = assertThrows(MyException.class, () ->
                productTypeService.createProductType(productTypeRequestDTO));
        assertEquals("El recurso: productType existe pero esta dado de baja", exception.getMessage());
    }

    @Test
    public void testCreateProductType_ProductTypeWithDifferentStatusExists_ReturnsConflictException() {
        ProductTypeRequestDTO productTypeRequestDTO = new ProductTypeRequestDTO();
        productTypeRequestDTO.setProductType("Product Type 1");

        ProductType objActualProductType = new ProductType();
        objActualProductType.setProductType("Product Type 1");
        objActualProductType.setStatus(true);

        when(productTypeRepository.findByProductType("Product Type 1")).thenReturn(objActualProductType);

        MyException exception = assertThrows(MyException.class, () ->
                productTypeService.createProductType(productTypeRequestDTO));
        assertEquals("El recurso: productType que desea crear ya existe", exception.getMessage());
    }


    @Test
    public void testFindProductTypeById_ValidId_ReturnsResponseDTO() {

        Long productTypeId = 1L;
        ProductType objProductType = new ProductType();
        objProductType.setProductTypeId(productTypeId);

        ProductTypeResponseDTO expectedResponseDTO = new ProductTypeResponseDTO();
        expectedResponseDTO.setProductTypeId(productTypeId);

        when(productTypeRepository.findById(productTypeId)).thenReturn(Optional.of(objProductType));
        when(productTypeMapper.productTypeToResp(objProductType)).thenReturn(expectedResponseDTO);

        ProductTypeResponseDTO responseDTO = productTypeService.findProductTypeById(productTypeId);

        assertNotNull(responseDTO);
        assertEquals(productTypeId, responseDTO.getProductTypeId());
        verify(productTypeRepository, times(1)).findById(productTypeId);
        verify(productTypeMapper, times(1)).productTypeToResp(objProductType);
    }

    @Test
    public void testFindProductTypeByName_ValidName_ReturnsResponseDTO() {

        String productType = "Product Type 1";
        ProductType objProductType = new ProductType();
        objProductType.setProductType(productType);

        ProductTypeResponseDTO expectedResponseDTO = new ProductTypeResponseDTO();
        expectedResponseDTO.setProductType(productType);

        when(productTypeRepository.findByProductType(productType)).thenReturn(objProductType);
        when(productTypeMapper.productTypeToResp(objProductType)).thenReturn(expectedResponseDTO);

        ProductTypeResponseDTO responseDTO = productTypeService.findProductTypeByName(productType);

        assertNotNull(responseDTO);
        assertEquals(productType, responseDTO.getProductType());
        verify(productTypeRepository, times(1)).findByProductType(productType);
        verify(productTypeMapper, times(1)).productTypeToResp(objProductType);
    }

    @Test
    public void testChangeStatus_ExistingProductType_ChangesStatus() {
        Long productTypeId = 1L;
        ProductType productType = new ProductType();
        productType.setProductTypeId(productTypeId);
        productType.setStatus(true);

        when(productTypeRepository.findById(productTypeId)).thenReturn(Optional.of(productType));

        productTypeService.changeStatus(productTypeId);

        assertFalse(productType.getStatus());
        verify(productTypeRepository, times(1)).save(productType);
    }


}