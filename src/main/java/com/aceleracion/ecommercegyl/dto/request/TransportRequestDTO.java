package com.aceleracion.ecommercegyl.dto.request;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;


public class TransportRequestDTO {



    @NotNull(message = "Debe ingresar la sucursal origen.")
    @Positive(message = "El la sucursal destino debe ser mayor a 0.")
    private Long originBranchId;

    @NotNull(message = "Debe ingresar la sucursal destino.")
    @Positive(message = "El la sucursal destino debe ser mayor a 0.")
    private Long targetBranchId;
    @NotEmpty(message = "La lista no puede estar vacía")

    private List<Long> productsCode;

    public TransportRequestDTO() {
    }

    public Long getOriginBranchId() {
        return originBranchId;
    }

    public void setOriginBranchId(Long originBranchId) {
        this.originBranchId = originBranchId;
    }

    public Long getTargetBranchId() {
        return targetBranchId;
    }

    public void setTargetBranchId(Long targetBranchId) {
        this.targetBranchId = targetBranchId;
    }

    public List<Long> getProductsCode() {
        return productsCode;
    }

    public void setProductsCode(List<Long> productsCode) {
        this.productsCode = productsCode;
    }

    public TransportRequestDTO(Long originBranchId, Long targetBranchId, List<Long> productsCode) {
        this.originBranchId = originBranchId;
        this.targetBranchId = targetBranchId;
        this.productsCode = productsCode;


    }
}
