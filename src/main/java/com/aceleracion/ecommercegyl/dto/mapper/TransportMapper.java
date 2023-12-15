package com.aceleracion.ecommercegyl.dto.mapper;


import com.aceleracion.ecommercegyl.dto.request.TransportRequestDTO;

import com.aceleracion.ecommercegyl.dto.response.TransportResponseDTO;

import com.aceleracion.ecommercegyl.model.Transport;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = {BranchMapper.class, ProductMapper.class})
@Component
public interface TransportMapper {

    Transport reqToTransport(TransportRequestDTO transportRequestDTO);

    @Mapping(source = "originBranch", target = "originBranchResponseDTO")
    @Mapping(source = "targetBranch", target = "targetBranchResponseDTO")
    @Mapping(source = "products", target = "productResponseDTOS")
    TransportResponseDTO transportToResp(Transport transport);

    List<TransportResponseDTO> transportToRespList(List<Transport> transports);
}
