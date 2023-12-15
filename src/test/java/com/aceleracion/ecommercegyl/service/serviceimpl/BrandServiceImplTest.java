package com.aceleracion.ecommercegyl.service.serviceimpl;

import com.aceleracion.ecommercegyl.dto.mapper.BrandMapper;
import com.aceleracion.ecommercegyl.dto.request.BrandRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.BrandResponseDTO;
import com.aceleracion.ecommercegyl.model.Brand;
import com.aceleracion.ecommercegyl.repository.BrandRepository;
import com.aceleracion.ecommercegyl.repository.serviceimpl.BrandServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


class BrandServiceImplTest {

    @InjectMocks
    private BrandServiceImpl brandService;

    @Mock
    private BrandRepository brandRepository;

    @Mock
    private BrandMapper brandMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        brandService = new BrandServiceImpl(brandRepository, brandMapper);
    }

    @Test
    void testCreateBrand() throws Exception {


        BrandRequestDTO requestDTO = new BrandRequestDTO();
        requestDTO.setName("Test Brand");

        Brand existingBrand = Mockito.mock(Brand.class);
        when(brandRepository.findByName(Mockito.anyString())).thenReturn(existingBrand, null);

        Brand objBrand = new Brand();
        when(brandMapper.reqToBrand(Mockito.any(BrandRequestDTO.class))).thenReturn(objBrand);

        BrandResponseDTO expectedResponseDTO = new BrandResponseDTO();
        expectedResponseDTO.setName("Test Brand");
        when(brandMapper.brandToResp(Mockito.any(Brand.class))).thenReturn(expectedResponseDTO);


        BrandResponseDTO result = brandService.createBrand(requestDTO);

        assertNotNull(result);
        assertEquals("Test Brand", result.getName());
        assertTrue(objBrand.getStatus());
        Mockito.verify(brandRepository, Mockito.times(1)).save(objBrand);
    }

    @Test
    void testFindBrandByName() throws Exception {
        BrandRequestDTO requestDTO = new BrandRequestDTO();
        requestDTO.setName("Test Brand");

        Brand objBrand = new Brand();
        objBrand.setName("Test Brand");

        when(brandMapper.reqToBrand(Mockito.any(BrandRequestDTO.class))).thenReturn(objBrand);
        when(brandRepository.findByName(Mockito.anyString())).thenReturn(objBrand);

        BrandResponseDTO expectedResponseDTO = new BrandResponseDTO();
        expectedResponseDTO.setName("Test Brand");
        when(brandMapper.brandToResp(Mockito.any(Brand.class))).thenReturn(expectedResponseDTO);


        BrandResponseDTO result = brandService.findBrandByName(requestDTO);

        assertNotNull(result);
        assertEquals("Test Brand", result.getName());

    }

    @Test
    void updateBrandWithExistingBrandNameReturnsUpdatedBrandResponseDTO() {
        String existingBrandName = "Existing Brand";
        BrandRequestDTO brandRequestDTO = new BrandRequestDTO();
        brandRequestDTO.setName("Updated Brand");

        Brand existingBrand = new Brand();
        existingBrand.setName(existingBrandName);

        BrandMapper brandMapper = Mockito.mock(BrandMapper.class);
        BrandRepository brandRepository = Mockito.mock(BrandRepository.class);

        BrandServiceImpl brandService = new BrandServiceImpl(brandRepository, brandMapper);

        BrandResponseDTO expectedResponseDTO = new BrandResponseDTO();
        when(brandMapper.brandToResp(existingBrand)).thenReturn(expectedResponseDTO);

        when(brandRepository.findByName(existingBrandName)).thenReturn(existingBrand);

        BrandResponseDTO result = brandService.updateBrand(existingBrandName, brandRequestDTO);

        assertNotNull(result);
        assertEquals(expectedResponseDTO, result);
    }

}