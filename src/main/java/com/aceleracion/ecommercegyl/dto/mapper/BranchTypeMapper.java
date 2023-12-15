package com.aceleracion.ecommercegyl.dto.mapper;

import com.aceleracion.ecommercegyl.dto.request.BranchTypeRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.BranchTypeResponseDTO;
import com.aceleracion.ecommercegyl.model.BranchType;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Mapper(componentModel = "spring")
public interface BranchTypeMapper {

    BranchType reqToBranchType(BranchTypeRequestDTO branchTypeRequestDTO);

    BranchTypeResponseDTO branchTypeToResp(BranchType branchType);

    List<BranchTypeResponseDTO> branchTypeToRespList(List<BranchType> branchTypes);
}
