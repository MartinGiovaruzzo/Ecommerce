package com.aceleracion.ecommercegyl.service.service;
import com.aceleracion.ecommercegyl.dto.request.PaymentMethodRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.PaymentMethodResponseDTO;
import com.aceleracion.ecommercegyl.model.PaymentMethod;

import java.util.List;

public interface PaymentMethodService {
    PaymentMethodResponseDTO createPaymentMethod(PaymentMethodRequestDTO paymentMethodRequestDTO) ;

    PaymentMethodResponseDTO findPaymentMethodById(Long id);

    PaymentMethod findById(Long id);

    List<PaymentMethodResponseDTO> findAllPaymentMethods();

    void changeStatus(Long id);

    List<PaymentMethodResponseDTO> findAllPaymentMethodByStatusTrue();
}
