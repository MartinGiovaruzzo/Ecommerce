package com.aceleracion.ecommercegyl.service.service;

import com.aceleracion.ecommercegyl.dto.request.BranchRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.BranchResponseDTO;
import com.aceleracion.ecommercegyl.model.Branch;

import java.util.List;

public interface BranchService {
    BranchResponseDTO createBranch(BranchRequestDTO branchRequestDTO) ;

    BranchResponseDTO findBranchById(Long branchId);

    Branch findById(Long branchId);

    List<BranchResponseDTO> findBranchByName(String branchName);

    BranchResponseDTO findBranchByAddress(String street, String number, Long cityId);

    List<BranchResponseDTO> findAllBranches();

    BranchResponseDTO updateBranch(Long branchId, BranchRequestDTO branchRequestDTO) ;

    void changeStatus(Long branchId);

    List<BranchResponseDTO> findAllBranchByStatusTrue();

    void saveBranch(Branch branch);
}
