package com.aceleracion.ecommercegyl.service.service;

import com.aceleracion.ecommercegyl.dto.request.TransportRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.TransportResponseDTO;
import java.text.ParseException;
import java.util.List;

public interface TransportService {

    TransportResponseDTO createTransport(TransportRequestDTO transportRequestDTO) ;

    TransportResponseDTO findTransportById(Long id);

    List<TransportResponseDTO> findAllTransports();

    List<TransportResponseDTO> findTransportByDate(String transportDate) throws ParseException ;

    List<TransportResponseDTO> findTransportByTargetBranch(Long branchId);

    List<TransportResponseDTO> findTransportByOriginBranch(Long branchId);

    List<TransportResponseDTO> findTransportByOriginBranchDate(Long branch, String date) throws ParseException;

    List<TransportResponseDTO> findTransportByTargetBranchDate(Long branch, String date) throws ParseException;
}
