package com.aceleracion.ecommercegyl.service.serviceimpl;

import com.aceleracion.ecommercegyl.dto.mapper.InvoiceTypeMapper;
import com.aceleracion.ecommercegyl.dto.request.InvoiceTypeRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.InvoiceTypeResponseDTO;
import com.aceleracion.ecommercegyl.exception.MyException;
import com.aceleracion.ecommercegyl.model.InvoiceType;
import com.aceleracion.ecommercegyl.repository.InvoiceTypeRepository;
import com.aceleracion.ecommercegyl.repository.serviceimpl.InvoiceTypeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InvoiceTypeServiceImplTest {

    @Mock
    private InvoiceTypeRepository invoiceTypeRepository;

    @Mock
    private InvoiceTypeMapper invoiceTypeMapper;

    @InjectMocks
    private InvoiceTypeServiceImpl invoiceTypeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        invoiceTypeService = new InvoiceTypeServiceImpl(invoiceTypeRepository, invoiceTypeMapper);
    }

    @Test
    void createInvoiceType_ValidRequest_ReturnsResponseDTO() throws MyException {
        // Arrange
        InvoiceTypeRequestDTO requestDTO = new InvoiceTypeRequestDTO();
        InvoiceType invoiceType = new InvoiceType();
        InvoiceTypeResponseDTO responseDTO = new InvoiceTypeResponseDTO();

        when(invoiceTypeMapper.reqToInvType(requestDTO)).thenReturn(invoiceType);
        when(invoiceTypeRepository.findInvoiceTypeByInvoiceName(any())).thenReturn(null);
        when(invoiceTypeMapper.invTypeToResp(invoiceType)).thenReturn(responseDTO);
        when(invoiceTypeRepository.save(invoiceType)).thenReturn(invoiceType);

        // Act
        InvoiceTypeResponseDTO result = invoiceTypeService.createInvoiceType(requestDTO);

        // Assert
        assertNotNull(result);
        assertEquals(responseDTO, result);
        verify(invoiceTypeRepository, times(1)).save(invoiceType);
    }


    @Test
    void findInvoiceTypeByIdWithValidIdReturnsInvoiceResponseDTO() {
        InvoiceType invoiceType = new InvoiceType();
        String invoiceTypeName = "InvoiceName";
        invoiceType.setInvoiceTypeId(1L);
        invoiceType.setInvoiceName(invoiceTypeName);
        invoiceType.setStatus(true);

        when(invoiceTypeRepository.findById(1L)).thenReturn(Optional.of(invoiceType));

        InvoiceTypeResponseDTO invoiceTypeResponseDTO = new InvoiceTypeResponseDTO();
        when(invoiceTypeMapper.invTypeToResp(invoiceType)).thenReturn(invoiceTypeResponseDTO);

        InvoiceTypeResponseDTO result;
        try {
            result = invoiceTypeService.findInvoiceTypeById(1L);
            assertNotNull(result);
            assertEquals(invoiceTypeResponseDTO, result);
        } catch (MyException e) {
            fail("Unexpected MyException was thrown: " + e.getMessage());
        }
    }

    @Test
    void findInvoiceTypeByIdWithInvalidIdThrowsMyException() {

        try {
            when(invoiceTypeRepository.findById(2L)).thenReturn(Optional.empty());

            assertThrows(MyException.class, () -> invoiceTypeService.findInvoiceTypeById(2L));

            verify(invoiceTypeRepository, times(1)).findById(2L);
        } catch (MyException ex) {
            System.out.println(ex.getMessage());
        }

    }

}