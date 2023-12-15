package com.aceleracion.ecommercegyl.service.serviceimpl;

import com.aceleracion.ecommercegyl.dto.mapper.AddressMapper;
import com.aceleracion.ecommercegyl.dto.mapper.BranchMapper;
import com.aceleracion.ecommercegyl.dto.mapper.BranchTypeMapper;
import com.aceleracion.ecommercegyl.dto.mapper.CityMapper;
import com.aceleracion.ecommercegyl.dto.request.AddressRequestDTO;
import com.aceleracion.ecommercegyl.dto.request.BranchRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.AddressResponseDTO;
import com.aceleracion.ecommercegyl.dto.response.BranchResponseDTO;
import com.aceleracion.ecommercegyl.dto.response.BranchTypeResponseDTO;
import com.aceleracion.ecommercegyl.dto.response.CityResponseDTO;
import com.aceleracion.ecommercegyl.exception.MyException;
import com.aceleracion.ecommercegyl.model.*;
import com.aceleracion.ecommercegyl.repository.BranchRepository;
import com.aceleracion.ecommercegyl.repository.serviceimpl.BranchServiceImpl;
import com.aceleracion.ecommercegyl.service.service.AddressService;
import com.aceleracion.ecommercegyl.service.service.BranchTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class BranchServiceImplTest {
    @InjectMocks
    private BranchServiceImpl branchService;
    @Mock
    private BranchRepository branchRepository;
    @Mock
    private BranchMapper branchMapper;
    @Mock
    private AddressService addressService;
    @Mock
    private AddressMapper addressMapper;
    @Mock
    private BranchTypeService branchTypeService;
    @Mock
    private BranchTypeMapper branchTypeMapper;
    @Mock
    private CityMapper cityMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        branchService = new BranchServiceImpl(
                branchMapper, branchRepository, addressService,addressMapper,branchTypeService,cityMapper);
    }

    @Test
    void testFindAllBranches() {

        List<Branch> branches = new ArrayList<>();

        Branch branch1 = new Branch();
        branch1.setBranchName("Cotto");
        branches.add(branch1);

        Branch branch2 = new Branch();
        branch2.setBranchName("Waltmar");
        branches.add(branch2);

        when(branchRepository.findAllByStatus(true)).thenReturn(branches);

        List<BranchResponseDTO> expectedResponse = new ArrayList<>();

        BranchResponseDTO branchResponseDTO1 = branchMapper.branchToResp(branch1);
        expectedResponse.add(branchResponseDTO1);

        BranchResponseDTO branchResponseDTO2 = branchMapper.branchToResp(branch2);
        expectedResponse.add(branchResponseDTO2);

        when(branchMapper.branchToRespList(branches)).thenReturn(expectedResponse);

        List<BranchResponseDTO> actualResponse = branchService.findAllBranches();

        assertNotNull(actualResponse);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void createBranch_WhenValidRequest_ReturnsResponse() throws MyException {

        BranchRequestDTO requestDTO = new BranchRequestDTO();
        requestDTO.setBranchName("Cotto");
        requestDTO.setPhoneNumber("456456466");
        requestDTO.setBranchTypeId(1L);
        requestDTO.setAddressReqDTO(new AddressRequestDTO("Juan Jose", "123", 1L));

        AddressRequestDTO addressRequestDTO = new AddressRequestDTO();
        addressRequestDTO.setStreet("Belgrano");
        addressRequestDTO.setNumber("11");
        addressRequestDTO.setCityId(1L);

        BranchType branchType = new BranchType();
        branchType = createObject.createBranchTypeTest();

        Address address = new Address();
        address = createObject.createAddressTest();

        Branch branch = new Branch();
        branch = createObject.createBranchTest();

        BranchResponseDTO expectedResponse = new BranchResponseDTO();
        expectedResponse.setBranchId(1L);
        expectedResponse.setBranchName("Carrefour");
        expectedResponse.setPhoneNumber("7897897899");

        when(branchRepository.findByAddress(anyString(), anyString(), anyLong())).thenReturn(null);
        when(branchTypeService.findById(anyLong())).thenReturn(branchType);
        when(addressService.createAddress(anyString(), anyString(), anyLong())).thenReturn(address);
        when(branchMapper.reqToBranch(any(BranchRequestDTO.class))).thenReturn(branch);
        when(branchMapper.branchToResp(any(Branch.class))).thenReturn(expectedResponse);
        when(cityMapper.cityToResp(any(City.class))).thenReturn(new CityResponseDTO());
        when(addressMapper.addressToResp(any(Address.class))).thenReturn(new AddressResponseDTO());
        System.out.println(requestDTO.getBranchName() + " asi llega");

        BranchResponseDTO actualResponse = branchService.createBranch(requestDTO);

        assertNotNull(actualResponse);

        verify(branchRepository, times(1)).save(branch);
        verify(branchTypeService, times(1)).findBranchTypeById(branchType.getBranchTypeId());
        verify(addressService, times(1)).createAddress(anyString(), anyString(), anyLong());
    }

    @Test
    void updateBranch_WhenValidIdAndRequest_ReturnsResponse() throws MyException {

        Long branchId = 1L;
        BranchRequestDTO requestDTO = new BranchRequestDTO();
        requestDTO.setBranchName("Updated Branch");
        requestDTO.setBranchTypeId(2L);
        requestDTO.setAddressReqDTO(new AddressRequestDTO("Updated Street", "456", 2L));

        Branch branch = createObject.createBranchTest();
        BranchType branchType = createObject.createBranchTypeTest();
        Address address = createObject.createAddressTest();

        when(branchRepository.findById(branchId)).thenReturn(Optional.of(branch));
        when(branchTypeService.findById(anyLong())).thenReturn(branchType);
        when(addressService.createAddress(anyString(), anyString(), anyLong())).thenReturn(address);
        when(branchMapper.branchToResp(any(Branch.class))).thenReturn(new BranchResponseDTO());
        when(branchTypeService.findBranchTypeById(anyLong())).thenReturn(new BranchTypeResponseDTO());
        when(cityMapper.cityToResp(any(City.class))).thenReturn(new CityResponseDTO());
        when(addressMapper.addressToResp(any(Address.class))).thenReturn(new AddressResponseDTO());

        BranchResponseDTO actualResponse = branchService.updateBranch(branchId, requestDTO);

        assertNotNull(actualResponse);

        verify(branchRepository, times(1)).findById(branchId);
        verify(branchRepository, times(1)).save(branch);
        verify(branchTypeService, times(1)).findById(requestDTO.getBranchTypeId());
        verify(addressService, times(1)).createAddress(anyString(), anyString(), anyLong());
    }



}
