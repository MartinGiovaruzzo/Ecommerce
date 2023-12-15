package com.aceleracion.ecommercegyl.repository.serviceimpl;

import com.aceleracion.ecommercegyl.dto.mapper.PaymentMethodMapper;
import com.aceleracion.ecommercegyl.dto.request.PaymentMethodRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.PaymentMethodResponseDTO;
import com.aceleracion.ecommercegyl.exception.MyException;
import com.aceleracion.ecommercegyl.model.PaymentMethod;
import com.aceleracion.ecommercegyl.repository.PaymentMethodRepository;
import com.aceleracion.ecommercegyl.service.service.PaymentMethodService;
import com.aceleracion.ecommercegyl.utils.ListValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentMethodServiceImpl implements PaymentMethodService {

    private final PaymentMethodRepository paymentMethodRepository;
    private final PaymentMethodMapper paymentMethodMapper;

    public PaymentMethodServiceImpl(PaymentMethodRepository paymentMethodRepository, PaymentMethodMapper paymentMethodMapper) {
        this.paymentMethodRepository = paymentMethodRepository;
        this.paymentMethodMapper = paymentMethodMapper;
    }

    @Transactional
    @Override
    public PaymentMethodResponseDTO createPaymentMethod(PaymentMethodRequestDTO paymentMethodRequestDTO) {
        if (paymentMethodRequestDTO != null) {
            PaymentMethod objPaymentMethod = paymentMethodMapper.reqToPaymentMethod(paymentMethodRequestDTO);
            if (paymentMethodRepository.findByPaymentMethodName(objPaymentMethod.getPaymentMethodName()) != null) {
                throw new MyException("existDB", "paymentMethod");
            }

            objPaymentMethod.setStatus(true);
            paymentMethodRepository.save(objPaymentMethod);
            return paymentMethodMapper.paymentMethodToResp(objPaymentMethod);
        } else {
            throw new MyException("entityNull", "paymentMethodRequest");
        }
    }

    @Override
    public PaymentMethodResponseDTO findPaymentMethodById(Long paymentMethodId) {
        if (paymentMethodId == null) {
            throw new MyException("entityNull", "paymentMethodId");
        }
        Optional<PaymentMethod> objpaymentMethod = paymentMethodRepository.findById(paymentMethodId);
        if (objpaymentMethod.isPresent()) {
            return paymentMethodMapper.paymentMethodToResp(objpaymentMethod.get());
        } else {
            throw new MyException("noExistDB", "paymentMethod");
        }
    }

    @Override
    public PaymentMethod findById(Long paymentMethodId) {
        if (paymentMethodId == null) {
            throw new MyException("entityNull", "paymentMethodId");
        }
        Optional<PaymentMethod> objpaymentMethod = paymentMethodRepository.findById(paymentMethodId);
        if (objpaymentMethod.isPresent()) {
            return objpaymentMethod.get();
        } else {
            throw new MyException("noExistDB", "paymentMethod");
        }
    }

    @Override
    public List<PaymentMethodResponseDTO> findAllPaymentMethods() {
        List<PaymentMethod> paymentMethods = paymentMethodRepository.findAll();
        ListValidator.validateResponseList(paymentMethods);

        return paymentMethodMapper.paymentMethodToRespList(paymentMethods);

    }

    @Override
    public void changeStatus(Long paymentMethodId) {
        if (paymentMethodId == null) {
            throw new MyException("entityNull", "paymentMethodId");
        }
        Optional<PaymentMethod> optionalPaymentMethods = paymentMethodRepository.findById(paymentMethodId);

        if (optionalPaymentMethods.isPresent()) {

            PaymentMethod objPaymentMethods = optionalPaymentMethods.get();
            boolean status = objPaymentMethods.getStatus();

            objPaymentMethods.setStatus(!status);

            paymentMethodRepository.save(objPaymentMethods);
        } else {
            throw new MyException("noExistDB", "paymentMethod");
        }

    }

    @Override
    public List<PaymentMethodResponseDTO> findAllPaymentMethodByStatusTrue() {
        List<PaymentMethod> paymentMethodsStatusTrue = paymentMethodRepository.findAllPaymentMethodByStatusTrue();
        ListValidator.validateResponseList(paymentMethodsStatusTrue);

        return paymentMethodMapper.paymentMethodToRespList(paymentMethodsStatusTrue);

    }
}


