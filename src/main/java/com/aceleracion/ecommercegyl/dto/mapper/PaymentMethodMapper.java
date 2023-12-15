package com.aceleracion.ecommercegyl.dto.mapper;
import com.aceleracion.ecommercegyl.dto.request.PaymentMethodRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.PaymentMethodResponseDTO;
import com.aceleracion.ecommercegyl.model.PaymentMethod;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface PaymentMethodMapper {

    PaymentMethod reqToPaymentMethod(PaymentMethodRequestDTO paymentMethodRequestDTO);
    PaymentMethodResponseDTO paymentMethodToResp(PaymentMethod paymentMethod);

    List<PaymentMethodResponseDTO> paymentMethodToRespList(List<PaymentMethod> paymentMethods);
}
