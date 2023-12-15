package com.aceleracion.ecommercegyl.dto.response;

import java.util.Date;
import java.util.List;

public class TransportResponseDTO {
    private Long transportId;
    private Date date;
    private BranchResponseDTO originBranchResponseDTO;
    private BranchResponseDTO targetBranchResponseDTO;
    private List<ProductResponseDTO> productResponseDTOS;

    public TransportResponseDTO() {
    }

    public TransportResponseDTO(Long transportId, Date date, BranchResponseDTO originBranchResponseDTO,
                                BranchResponseDTO targetBranchResponseDTO, List<ProductResponseDTO> productResponseDTOS) {
        this.transportId = transportId;
        this.date = date;
        this.originBranchResponseDTO = originBranchResponseDTO;
        this.targetBranchResponseDTO = targetBranchResponseDTO;
        this.productResponseDTOS = productResponseDTOS;
    }

    public Long getTransportId() {
        return transportId;
    }

    public void setTransportId(Long transportId) {
        this.transportId = transportId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BranchResponseDTO getOriginBranchResponseDTO() {
        return originBranchResponseDTO;
    }

    public void setOriginBranchResponseDTO(BranchResponseDTO originBranchResponseDTO) {
        this.originBranchResponseDTO = originBranchResponseDTO;
    }

    public BranchResponseDTO getTargetBranchResponseDTO() {
        return targetBranchResponseDTO;
    }

    public void setTargetBranchResponseDTO(BranchResponseDTO targetBranchResponseDTO) {
        this.targetBranchResponseDTO = targetBranchResponseDTO;
    }

    public List<ProductResponseDTO> getProductResponseDTOS() {
        return productResponseDTOS;
    }

    public void setProductResponseDTOS(List<ProductResponseDTO> productResponseDTOS) {
        this.productResponseDTOS = productResponseDTOS;
    }
}
