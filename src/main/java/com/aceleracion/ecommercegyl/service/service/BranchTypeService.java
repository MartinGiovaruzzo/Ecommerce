package com.aceleracion.ecommercegyl.service.service;

import com.aceleracion.ecommercegyl.dto.request.BranchTypeRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.BranchTypeResponseDTO;
import com.aceleracion.ecommercegyl.model.BranchType;

import java.util.List;

public interface BranchTypeService {

    BranchTypeResponseDTO createBranchType(BranchTypeRequestDTO branchTypeReqDTO) ;

    BranchTypeResponseDTO findBranchTypeById(Long branchTypeId);

    BranchType findById(Long branchTypeId);

    BranchTypeResponseDTO findBranchTypeByName(String branchType);
    List<BranchTypeResponseDTO> findAllBranchTypes();
    void changeStatus(String branchTypeName);
    List<BranchTypeResponseDTO> findAllByStatusTrue();
}
