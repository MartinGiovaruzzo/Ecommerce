package com.aceleracion.ecommercegyl.service.serviceimpl;

import com.aceleracion.ecommercegyl.dto.mapper.PaymentMethodMapper;
import com.aceleracion.ecommercegyl.dto.request.PaymentMethodRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.PaymentMethodResponseDTO;
import com.aceleracion.ecommercegyl.exception.MyException;
import com.aceleracion.ecommercegyl.model.PaymentMethod;
import com.aceleracion.ecommercegyl.repository.PaymentMethodRepository;
import com.aceleracion.ecommercegyl.repository.serviceimpl.PaymentMethodServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PaymentMethodServiceImplTest {
    @Mock
    private PaymentMethodRepository paymentMethodRepository;

    @Mock
    private PaymentMethodMapper paymentMethodMapper;

    @InjectMocks
    private PaymentMethodServiceImpl paymentMethodService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        paymentMethodService = new PaymentMethodServiceImpl(paymentMethodRepository, paymentMethodMapper);
    }

    @Test
    public void createPaymentMethod_ValidRequest_ReturnsResponseDTO() throws MyException {
        // Arrange
        PaymentMethodRequestDTO requestDTO = new PaymentMethodRequestDTO();
        PaymentMethod paymentMethod = new PaymentMethod();
        PaymentMethodResponseDTO responseDTO = new PaymentMethodResponseDTO();

        when(paymentMethodMapper.reqToPaymentMethod(requestDTO)).thenReturn(paymentMethod);
        when(paymentMethodRepository.findByPaymentMethodName(any(String.class))).thenReturn(null);
        when(paymentMethodMapper.paymentMethodToResp(paymentMethod)).thenReturn(responseDTO);
        when(paymentMethodRepository.save(paymentMethod)).thenReturn(paymentMethod);

        // Act
        PaymentMethodResponseDTO result = paymentMethodService.createPaymentMethod(requestDTO);

        // Assert
        assertNotNull(result);
        assertEquals(responseDTO, result);
        verify(paymentMethodRepository, times(1)).save(paymentMethod);
    }
    @Test
    public void findPaymentMethodById_ValidId_ReturnsResponseDTO() {
        // Arrange
        Long id = 1L;
        PaymentMethod paymentMethod = new PaymentMethod();
        PaymentMethodResponseDTO responseDTO = new PaymentMethodResponseDTO();

        when(paymentMethodRepository.findById(id)).thenReturn(Optional.of(paymentMethod));
        when(paymentMethodMapper.paymentMethodToResp(paymentMethod)).thenReturn(responseDTO);

        // Act
        PaymentMethodResponseDTO result = paymentMethodService.findPaymentMethodById(id);

        // Assert
        assertNotNull(result);
        assertEquals(responseDTO, result);
    }

    @Test
    public void findPaymentMethodById_NullId_ThrowsException() {
        // Arrange
        Long id = null;

        // Act & Assert
        assertThrows(MyException.class, () -> {
            paymentMethodService.findPaymentMethodById(id);
        });
    }

    @Test
    public void findPaymentMethodById_NonExistentId_ThrowsException() {
        // Arrange
        Long id = 1L;

        when(paymentMethodRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(MyException.class, () -> {
            paymentMethodService.findPaymentMethodById(id);
        });
    }

    @Test
    public void testFindAllPaymentMethods() {
        // Arrange
        List<PaymentMethod> paymentMethods = new ArrayList<>();
        PaymentMethod paymentMethod1 = new PaymentMethod();
        paymentMethod1.setPaymentMethodId(1L);
        paymentMethod1.setPaymentMethodName("Payment Method 1");
        paymentMethods.add(paymentMethod1);
        PaymentMethod paymentMethod2 = new PaymentMethod();
        paymentMethod2.setPaymentMethodId(2L);
        paymentMethod2.setPaymentMethodName("Payment Method 2");
        paymentMethods.add(paymentMethod2);

        List<PaymentMethodResponseDTO> expectedResponseDTOs = new ArrayList<>();
        PaymentMethodResponseDTO responseDTO1 = new PaymentMethodResponseDTO();
        responseDTO1.setPaymentMethodId(1L);
        responseDTO1.setPaymentMethodName("Payment Method 1");
        expectedResponseDTOs.add(responseDTO1);
        PaymentMethodResponseDTO responseDTO2 = new PaymentMethodResponseDTO();
        responseDTO2.setPaymentMethodId(2L);
        responseDTO2.setPaymentMethodName("Payment Method 2");
        expectedResponseDTOs.add(responseDTO2);

        when(paymentMethodRepository.findAll()).thenReturn(paymentMethods);
        when(paymentMethodMapper.paymentMethodToRespList(paymentMethods)).thenReturn(expectedResponseDTOs);

        // Act
        List<PaymentMethodResponseDTO> result = paymentMethodService.findAllPaymentMethods();

        // Assert
        assertEquals(expectedResponseDTOs, result);
        verify(paymentMethodRepository, times(1)).findAll();
        verify(paymentMethodMapper, times(1)).paymentMethodToRespList(paymentMethods);
    }

    @Test
    public void findAllPaymentMethodByStatusTrue_ReturnsListOfPaymentMethodResponseDTO() {
        // creo las listas y las lleno
        List<PaymentMethod> paymentMethods = new ArrayList<>();
        PaymentMethod paymentMethod1 = new PaymentMethod();
        paymentMethod1.setPaymentMethodId(1L);
        PaymentMethod paymentMethod2 = new PaymentMethod();
        paymentMethod2.setPaymentMethodId(2L);
        paymentMethods.add(paymentMethod1);
        paymentMethods.add(paymentMethod2);

        List<PaymentMethodResponseDTO> expectedResponseDTOs = new ArrayList<>();
        PaymentMethodResponseDTO responseDTO1 = new PaymentMethodResponseDTO();
        responseDTO1.setPaymentMethodId(1L);
        PaymentMethodResponseDTO responseDTO2 = new PaymentMethodResponseDTO();
        responseDTO2.setPaymentMethodId(2L);
        expectedResponseDTOs.add(responseDTO1);
        expectedResponseDTOs.add(responseDTO2);

        when(paymentMethodRepository.findAllPaymentMethodByStatusTrue()).thenReturn(paymentMethods);
        when(paymentMethodMapper.paymentMethodToRespList(paymentMethods)).thenReturn(expectedResponseDTOs);

        // Act
        List<PaymentMethodResponseDTO> result = paymentMethodService.findAllPaymentMethodByStatusTrue();

        // Assert
        assertEquals(expectedResponseDTOs, result);
        verify(paymentMethodRepository, times(1)).findAllPaymentMethodByStatusTrue();
        verify(paymentMethodMapper, times(1)).paymentMethodToRespList(paymentMethods);
    }


}


