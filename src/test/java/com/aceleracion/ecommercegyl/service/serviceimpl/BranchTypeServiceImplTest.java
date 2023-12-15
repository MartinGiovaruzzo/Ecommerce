package com.aceleracion.ecommercegyl.service.serviceimpl;

import com.aceleracion.ecommercegyl.dto.mapper.BranchTypeMapper;
import com.aceleracion.ecommercegyl.dto.request.BranchTypeRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.BranchTypeResponseDTO;
import com.aceleracion.ecommercegyl.exception.MyException;
import com.aceleracion.ecommercegyl.model.BranchType;
import com.aceleracion.ecommercegyl.repository.BranchTypeRepository;
import com.aceleracion.ecommercegyl.repository.serviceimpl.BranchTypeServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BranchTypeServiceImplTest {
    @Mock
    private BranchTypeRepository branchTypeRepository;
    @Mock
    private BranchTypeMapper branchTypeMapper;
    @InjectMocks
    private BranchTypeServiceImpl branchTypeService;
    private BranchType branchType;

    @Test
    void createBranchTypeWithExistingActiveBranchTypeThrowsException() {
        try {
            String branchTypeName = "Tienda";

            BranchType existingActiveBranchType = new BranchType();
            existingActiveBranchType.setBranchType(branchTypeName);
            existingActiveBranchType.setStatus(true);

            BranchTypeRequestDTO branchTypeRequestDTO = new BranchTypeRequestDTO();
            branchTypeRequestDTO.setBranchType(branchTypeName);

            when(branchTypeRepository.findByBranchType(branchTypeName)).thenReturn(existingActiveBranchType);

            branchTypeService.createBranchType(branchTypeRequestDTO);
            fail("Expected exception was not thrown.");
        } catch (MyException ex) {
            assertEquals("El recurso: branchType que desea crear ya existe", ex.getMessage());
        }
    }

    @Test
    void findBranchTypeByIdWithValidIdReturnsBranchTypeResponseDTO() {

        BranchType branchType = new BranchType();
        String branchTypeName = "Sucursal 1";
        branchType.setBranchTypeId(1L);
        branchType.setBranchType(branchTypeName);
        branchType.setStatus(true);

        when(branchTypeRepository.findById(1L)).thenReturn(Optional.of(branchType));

        BranchTypeResponseDTO branchTypeResponseDTO = new BranchTypeResponseDTO();
        when(branchTypeMapper.branchTypeToResp(branchType)).thenReturn(branchTypeResponseDTO);

        BranchTypeResponseDTO result;
        try {
            result = branchTypeService.findBranchTypeById(1L);

            assertNotNull(result);
            assertEquals(branchTypeResponseDTO, result);
        } catch (MyException e) {
            fail("Unexpected MyException was thrown: " + e.getMessage());
        }
    }



    @Test
    void findBranchTypeByIdWithInvalidIdThrowsMyException() {

        try {
            when(branchTypeRepository.findById(2L)).thenReturn(Optional.empty());

            assertThrows(MyException.class, () -> branchTypeService.findBranchTypeById(2L));

            verify(branchTypeRepository, times(1)).findById(2L);
        } catch (MyException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Test
    public void testChangeStatus_Success() {

        String branchType = "BranchType";
        BranchType objBranchType = new BranchType();
        objBranchType.setBranchType(branchType);
        objBranchType.setStatus(true);

        when(branchTypeRepository.findByBranchType(eq(branchType))).thenReturn(objBranchType);
        when(branchTypeRepository.save(any(BranchType.class))).thenReturn(objBranchType);

        branchTypeService.changeStatus(branchType);

        assertFalse(objBranchType.getStatus());
        verify(branchTypeRepository, times(1)).findByBranchType(eq(branchType));
        verify(branchTypeRepository, times(1)).save(any(BranchType.class));
    }

    @Test
    public void testChangeStatus_NullBranchType() {

        assertThrows(MyException.class, () -> branchTypeService.changeStatus(null));
        verify(branchTypeRepository, never()).findByBranchType(anyString());
        verify(branchTypeRepository, never()).save(any(BranchType.class));
    }

    @Test
    public void testChangeStatus_NonexistentBranchType() {
        String branchType = "NonexistentBranchType";

        when(branchTypeRepository.findByBranchType(eq(branchType))).thenReturn(null);

        assertThrows(MyException.class, () -> branchTypeService.changeStatus(branchType));
        verify(branchTypeRepository, times(1)).findByBranchType(eq(branchType));
        verify(branchTypeRepository, never()).save(any(BranchType.class));
    }


    }