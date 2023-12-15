package com.aceleracion.ecommercegyl.dto.mapper;

import com.aceleracion.ecommercegyl.dto.request.BranchRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.BranchResponseDTO;
import com.aceleracion.ecommercegyl.model.Branch;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring",
        uses = {BranchTypeMapper.class, AddressMapper.class})
public interface BranchMapper {

    Branch reqToBranch(BranchRequestDTO branchRequestDTO);

    @Mapping(source = "branchType", target = "branchTypeResponseDTO")
    @Mapping(source = "address", target = "addressResponseDTO")
    BranchResponseDTO branchToResp(Branch branch);

    List<BranchResponseDTO> branchToRespList(List<Branch> branches);
}
