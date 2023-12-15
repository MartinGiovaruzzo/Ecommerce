package com.aceleracion.ecommercegyl.service.serviceimpl;

import com.aceleracion.ecommercegyl.dto.mapper.TransportMapper;
import com.aceleracion.ecommercegyl.dto.request.TransportRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.TransportResponseDTO;
import com.aceleracion.ecommercegyl.exception.MyException;
import com.aceleracion.ecommercegyl.model.Branch;
import com.aceleracion.ecommercegyl.model.Product;
import com.aceleracion.ecommercegyl.model.Transport;
import com.aceleracion.ecommercegyl.repository.TransportRepository;
import com.aceleracion.ecommercegyl.repository.serviceimpl.TransportServiceImpl;
import com.aceleracion.ecommercegyl.service.service.BranchService;
import com.aceleracion.ecommercegyl.service.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransportServiceImplTest {

    @InjectMocks
    private TransportServiceImpl transportService;

    @Mock
    private TransportRepository transportRepository;

    @Mock
    private TransportMapper transportMapper;

    @Mock
    private BranchService branchService;

    @Mock
    private ProductService productService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        transportService = new TransportServiceImpl(transportRepository, transportMapper, branchService, productService);
    }

    @Test
    void createTransport_ValidRequest_ReturnsResponseDTO() throws MyException {

        TransportRequestDTO requestDTO = new TransportRequestDTO();
        requestDTO.setOriginBranchId(1L);
        requestDTO.setTargetBranchId(2L);
        List<Long> productsCode = new ArrayList<>();
        productsCode.add(1L);
        productsCode.add(2L);
        requestDTO.setProductsCode(productsCode);

        Branch originBranch = new Branch();
        originBranch.setBranchId(1L);
        Branch targetBranch = new Branch();
        targetBranch.setBranchId(2L);
        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId(1L);
        products.add(product1);
        Product product2 = new Product();
        product2.setProductId(2L);
        products.add(product2);

        when(branchService.findById(1L)).thenReturn(originBranch);
        when(branchService.findById(2L)).thenReturn(targetBranch);
        when(productService.findProductsByBranchModel(1L)).thenReturn(products);
        when(productService.findById(1L)).thenReturn(product1);
        when(productService.findById(2L)).thenReturn(product2);

        TransportResponseDTO expectedResponseDTO = new TransportResponseDTO();
        when(transportMapper.transportToResp(any(Transport.class))).thenReturn(expectedResponseDTO);

        TransportResponseDTO result = transportService.createTransport(requestDTO);

        assertNotNull(result);
        assertEquals(expectedResponseDTO, result);
        verify(transportRepository, times(1)).save(any(Transport.class));
    }

    @Test
    void createTransport_WithNullRequest_ThrowsException() {
        TransportRequestDTO transportRequestDTO = null;

        assertThrows(MyException.class, () -> {
            transportService.createTransport(transportRequestDTO);
        });
    }


    @Test
    void findTransportById_NonExistentId_ThrowsException() {

        Long transportId = 1L;

        when(transportRepository.findById(transportId)).thenReturn(Optional.empty());

        assertThrows(MyException.class, () -> transportService.findTransportById(transportId));
    }

    @Test
    void findTransportByOriginBranch_ValidBranchId_ReturnsListOfResponseDTOs() throws MyException {

        Long branchId = 1L;
        List<Transport> transports = new ArrayList<>();
        Transport transport1 = new Transport();
        transports.add(transport1);
        Transport transport2 = new Transport();
        transports.add(transport2);

        when(transportRepository.findTransportByOriginBranchBranchId(branchId)).thenReturn(transports);

        TransportResponseDTO responseDTO1 = new TransportResponseDTO();
        TransportResponseDTO responseDTO2 = new TransportResponseDTO();
        when(transportMapper.transportToRespList(transports)).thenReturn(List.of(responseDTO1, responseDTO2));

        List<TransportResponseDTO> result = transportService.findTransportByOriginBranch(branchId);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(responseDTO1, result.get(0));
        assertEquals(responseDTO2, result.get(1));
    }

}


