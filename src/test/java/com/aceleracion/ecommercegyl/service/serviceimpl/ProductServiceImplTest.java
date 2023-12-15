package com.aceleracion.ecommercegyl.service.serviceimpl;


import com.aceleracion.ecommercegyl.dto.mapper.ProductMapper;
import com.aceleracion.ecommercegyl.dto.request.ProductRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.ProductResponseDTO;
import com.aceleracion.ecommercegyl.model.Branch;
import com.aceleracion.ecommercegyl.model.Brand;
import com.aceleracion.ecommercegyl.model.Product;
import com.aceleracion.ecommercegyl.model.ProductType;
import com.aceleracion.ecommercegyl.repository.ProductRepository;

import com.aceleracion.ecommercegyl.repository.serviceimpl.BranchServiceImpl;
import com.aceleracion.ecommercegyl.repository.serviceimpl.BrandServiceImpl;
import com.aceleracion.ecommercegyl.repository.serviceimpl.ProductServiceImpl;
import com.aceleracion.ecommercegyl.repository.serviceimpl.ProductTypeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;
    @Mock
    private ProductMapper productMapper;
    @Mock
    private BrandServiceImpl brandService;
    @Mock
    private BranchServiceImpl branchService;
    @Mock
    private ProductTypeServiceImpl productTypeService;
    @InjectMocks
    private ProductServiceImpl productService;


    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

   

    @Test
    public void testFindAllProducts_ReturnsListOfResponseDTOs() {

        List<Product> products = new ArrayList<>();
        products.add(new Product());
        products.add(new Product());
        products.add(new Product());

        List<ProductResponseDTO> expectedResponseDTOs = new ArrayList<>();
        expectedResponseDTOs.add(new ProductResponseDTO());
        expectedResponseDTOs.add(new ProductResponseDTO());
        expectedResponseDTOs.add(new ProductResponseDTO());

        when(productRepository.findAll()).thenReturn(products);
        when(productMapper.productToRespList(products)).thenReturn(expectedResponseDTOs);

        List<ProductResponseDTO> responseDTOs = productService.findAllProducts();

        assertNotNull(responseDTOs);
        assertEquals(expectedResponseDTOs, responseDTOs);
        verify(productRepository, times(1)).findAll();
        verify(productMapper, times(1)).productToRespList(products);
    }

    @Test
    public void testFindProductByCode_ValidCode_ReturnsProduct() {

        Long productCode = 123456L;
        Product expectedProduct = new Product();

        when(productRepository.findByProductCode(productCode)).thenReturn(expectedProduct);

        Product actualProduct = productService.findProductByCode(productCode);

        assertNotNull(actualProduct);
        assertEquals(expectedProduct, actualProduct);
        verify(productRepository, times(1)).findByProductCode(productCode);
    }

    @Test
    public void testChangeProductStatus_ExistingProduct_StatusToggled() {

        Long productCode = 123456L;
        Product existingProduct = new Product();
        existingProduct.setProductCode(productCode);
        existingProduct.setStatus(true);

        when(productRepository.findByProductCode(productCode)).thenReturn(existingProduct);

        productService.changeProductStatus(productCode);

        assertFalse(existingProduct.getStatus());
        verify(productRepository, times(1)).save(existingProduct);
    }


}
