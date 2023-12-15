package com.aceleracion.ecommercegyl.service.serviceimpl;

import com.aceleracion.ecommercegyl.dto.mapper.DiscountRateMapper;
import com.aceleracion.ecommercegyl.dto.request.DiscountRateRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.DiscountRateResponseDTO;
import com.aceleracion.ecommercegyl.model.DiscountRate;
import com.aceleracion.ecommercegyl.repository.DiscountRateRepository;
import com.aceleracion.ecommercegyl.repository.serviceimpl.DiscountRateServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DiscountRateServiceImplTest {
    @InjectMocks
    private DiscountRateServiceImpl discountRateService;
    @Mock
    private DiscountRateRepository discountRateRepository;
    @Mock
    private DiscountRateMapper discountRateMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        discountRateService = new DiscountRateServiceImpl(discountRateRepository, discountRateMapper);
    }

    @Test
    void createDiscountRate() {
        DiscountRateRequestDTO request = new DiscountRateRequestDTO();
        request.setDiscountName("New Discount");

        DiscountRate discountRate = new DiscountRate();
        discountRate.setDiscountName("New Discount");

        when(discountRateMapper.reqToDiscountRate(any(DiscountRateRequestDTO.class))).thenReturn(discountRate);
        when(discountRateRepository.findByDiscountName(any(String.class))).thenReturn(null);
        when(discountRateMapper.discountRateToResp(any(DiscountRate.class))).thenReturn(new DiscountRateResponseDTO());
        doAnswer(invocation -> {
            DiscountRate discountRateArg = invocation.getArgument(0);
            DiscountRateResponseDTO responseDTO = new DiscountRateResponseDTO();
            responseDTO.setDiscountName(discountRateArg.getDiscountName());
            return responseDTO;
        }).when(discountRateMapper).discountRateToResp(eq(discountRate));

        DiscountRateResponseDTO response = discountRateService.createDiscountRate(request);

        assertNotNull(response);
        assertEquals("New Discount", response.getDiscountName());

        verify(discountRateRepository, times(1)).save(discountRate);
    }

    @Test
    void findAllDiscountRatesByStatusTrue() {
        List<DiscountRate> discountRates = new ArrayList<>();

        DiscountRate discountRate1 = createObject.createDiscountRateTest();
        DiscountRate discountRate2 = createObject.createDiscountRateTest();
        discountRate2.setDiscountRateId(3L);
        discountRates.add(discountRate1);
        discountRates.add(discountRate2);

        when(discountRateRepository.findAllByStatusTrue()).thenReturn(discountRates);

        List<DiscountRateResponseDTO> expectedResponse = new ArrayList<>();

        DiscountRateResponseDTO discountResponseDTO1 = discountRateMapper.discountRateToResp(discountRate1);
        expectedResponse.add(discountResponseDTO1);

        DiscountRateResponseDTO discountResponseDTO2 = discountRateMapper.discountRateToResp(discountRate2);
        expectedResponse.add(discountResponseDTO2);

        when(discountRateMapper.discountRatesListToRespList(discountRates)).thenReturn(expectedResponse);

        List<DiscountRateResponseDTO> actualResponse = discountRateService.findAllDiscountRatesByStatusTrue();

        assertNotNull(actualResponse);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void findAll() {
        List<DiscountRate> discountRates = new ArrayList<>();

        DiscountRate discountRate1 = createObject.createDiscountRateTest();
        DiscountRate discountRate2 = createObject.createDiscountRateTest();
        discountRate2.setDiscountRateId(2L);
        discountRate2.setDiscountName("Prueba");
        discountRates.add(discountRate1);
        discountRates.add(discountRate2);

        when(discountRateRepository.findAll()).thenReturn(discountRates);

        List<DiscountRateResponseDTO> expectedResponse = new ArrayList<>();

        DiscountRateResponseDTO discountResponseDTO1 = discountRateMapper.discountRateToResp(discountRate1);
        expectedResponse.add(discountResponseDTO1);

        DiscountRateResponseDTO discountResponseDTO2 = discountRateMapper.discountRateToResp(discountRate2);
        expectedResponse.add(discountResponseDTO2);

        when(discountRateMapper.discountRatesListToRespList(discountRates)).thenReturn(expectedResponse);

        List<DiscountRateResponseDTO> actualResponse = discountRateService.findAll();

        assertNotNull(actualResponse);
        assertEquals(expectedResponse, actualResponse);
    }
}