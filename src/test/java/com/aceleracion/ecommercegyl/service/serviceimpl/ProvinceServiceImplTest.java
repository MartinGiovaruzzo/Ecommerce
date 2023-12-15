package com.aceleracion.ecommercegyl.service.serviceimpl;
import com.aceleracion.ecommercegyl.dto.mapper.ProvinceMapper;
import com.aceleracion.ecommercegyl.dto.request.ProvinceRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.ProvinceResponseDTO;
import com.aceleracion.ecommercegyl.exception.MyException;
import com.aceleracion.ecommercegyl.model.Province;
import com.aceleracion.ecommercegyl.repository.ProvinceRepository;
import com.aceleracion.ecommercegyl.repository.serviceimpl.ProvinceServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProvinceServiceImplTest {

    @InjectMocks
    private ProvinceServiceImpl provinceService;

    @Mock
    private ProvinceRepository provinceRepository;

    @Mock
    private ProvinceMapper provinceMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        provinceService = new ProvinceServiceImpl(provinceRepository, provinceMapper);
    }

    @Test
    void testCreateProvince() throws MyException {

        ProvinceRequestDTO requestDTO = new ProvinceRequestDTO();
        requestDTO.setProvinceName("Test Province");

        Province province = new Province();
        province.setProvinceName("Test Province");

        when(provinceMapper.reqToProvince(any(ProvinceRequestDTO.class))).thenReturn(province);
        when(provinceRepository.findByProvinceName(any(String.class))).thenReturn(null);
        when(provinceMapper.provinceToResp(any(Province.class))).thenReturn(new ProvinceResponseDTO());
        doAnswer(invocation -> {
            Province provinceArg = invocation.getArgument(0);
            ProvinceResponseDTO responseDTO = new ProvinceResponseDTO();
            responseDTO.setProvinceName(provinceArg.getProvinceName());
            return responseDTO;
        }).when(provinceMapper).provinceToResp(eq(province));

        ProvinceResponseDTO result = provinceService.createProvince(requestDTO);

        assertNotNull(result);
        assertEquals("Test Province", result.getProvinceName());

        verify(provinceRepository, times(1)).save(province);
    }


    @Test
    void findProvinceResponseById() {
        long provinceId = 1L;
        Province province = new Province();
        province.setProvinceId(provinceId);
        province.setProvinceName("test");

        ProvinceResponseDTO expectedResponse = new ProvinceResponseDTO();
        expectedResponse.setProvinceName("test");

        when(provinceRepository.findById(provinceId)).thenReturn(Optional.of(province));
        when(provinceMapper.provinceToResp(province)).thenReturn(expectedResponse);


        ProvinceResponseDTO result = provinceService.findProvinceById(provinceId);

        assertNotNull(result);
        assertEquals("test", result.getProvinceName());

    }

    @Test
    void testFindAllProvinces() {
        Province province1 = new Province();
        province1.setProvinceName("Mendoza");
        Province province2 = new Province();
        province2.setProvinceName("Chaco");

        when(provinceRepository.findAll()).thenReturn(Arrays.asList(province1,province2));

        List<ProvinceResponseDTO> result = provinceService.findAllProvinces();
        assertNotNull(result);

    }
    @Test
    void testFindAllBranches() {

        List<Province> provinces = new ArrayList<>();

        Province province1 = new Province();
        province1.setProvinceName("Mendoza");
        provinces.add(province1);

        Province province2 = new Province();
        province2.setProvinceName("Chaco");
        provinces.add(province2);

        when(provinceRepository.findAll()).thenReturn(provinces);

        List<ProvinceResponseDTO> expectedResponse = new ArrayList<>();

        ProvinceResponseDTO provinceResponseDTO = provinceMapper.provinceToResp(province1);
        expectedResponse.add(provinceResponseDTO);

        ProvinceResponseDTO provinceResponseDTO2 = provinceMapper.provinceToResp(province2);
        expectedResponse.add(provinceResponseDTO2);

        when(provinceMapper.provinceToRespList(provinces)).thenReturn(expectedResponse);

        List<ProvinceResponseDTO> actualResponse = provinceService.findAllProvinces();

        assertNotNull(actualResponse);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void testChangeStatus() {

        Long provinceId = 1L;

        Province province = new Province();
        province.setStatus(true);

        Optional<Province> optional = Optional.of(province);

        when(provinceRepository.findById(provinceId)).thenReturn(optional);

        provinceService.changeStatus(provinceId);

        assertFalse(province.getStatus());
        verify(provinceRepository, times(1)).findById(provinceId);
        verify(provinceRepository, times(1)).save(province);
    }

}