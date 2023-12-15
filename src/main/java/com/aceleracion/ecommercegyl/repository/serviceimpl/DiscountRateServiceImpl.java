package com.aceleracion.ecommercegyl.repository.serviceimpl;

import com.aceleracion.ecommercegyl.dto.mapper.DiscountRateMapper;
import com.aceleracion.ecommercegyl.dto.request.DiscountRateRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.DiscountRateResponseDTO;
import com.aceleracion.ecommercegyl.exception.MyException;
import com.aceleracion.ecommercegyl.model.DiscountRate;
import com.aceleracion.ecommercegyl.repository.DiscountRateRepository;
import com.aceleracion.ecommercegyl.service.service.DiscountRateService;
import com.aceleracion.ecommercegyl.utils.ListValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DiscountRateServiceImpl implements DiscountRateService {

    private final DiscountRateRepository discountRateRepository;
    private final DiscountRateMapper discountRateMapper;

    public DiscountRateServiceImpl(DiscountRateRepository discountRateRepository, DiscountRateMapper discountRateMapper) {
        this.discountRateRepository = discountRateRepository;
        this.discountRateMapper = discountRateMapper;
    }

    @Transactional
    @Override
    public DiscountRateResponseDTO createDiscountRate(DiscountRateRequestDTO discountRateRequestDTO) {

        if (discountRateRequestDTO.getDiscountName() != null) {
            DiscountRate objDiscountRate = discountRateRepository.findByDiscountName(discountRateRequestDTO.getDiscountName());
            if (objDiscountRate == null) {
                objDiscountRate = discountRateMapper.reqToDiscountRate(discountRateRequestDTO);
                objDiscountRate.setStatus(true);
                discountRateRepository.save(objDiscountRate);
                return discountRateMapper.discountRateToResp(objDiscountRate);
            } else {
                throw new MyException("existDB", "discountRate");
            }
        } else {
            throw new MyException("entityNull", "discountRateRequest");
        }
    }

    @Override
    public DiscountRateResponseDTO findDiscountRateById(Long discountRateId) {
        if (discountRateId == null) {
            throw new MyException("entityNull", "discountRateId");
        }
        Optional<DiscountRate> objDiscountRate = discountRateRepository.findById(discountRateId);
        if (objDiscountRate.isPresent()) {
            return discountRateMapper.discountRateToResp(objDiscountRate.get());
        } else {
            throw new MyException("noExistDB", "discountRate");
        }
    }

    @Override
    public DiscountRate findById(Long discountRateId) {
        if (discountRateId == null) {
            throw new MyException("entityNull", "discountRateId");
        }
        Optional<DiscountRate> objDiscountRate = discountRateRepository.findById(discountRateId);
        if (objDiscountRate.isPresent()) {
            return objDiscountRate.get();
        } else {
            throw new MyException("noExistDB", "discountRate");
        }
    }

    @Override
    public void changeStatus(Long discountRateId) {
        if (discountRateId == null) {
            throw new MyException("entityNull", "discountRateId");
        }
        Optional<DiscountRate> optionalDiscountRate = discountRateRepository.findById(discountRateId);

        if (optionalDiscountRate.isPresent()) {

            DiscountRate objDiscountRate = optionalDiscountRate.get();

            objDiscountRate.setStatus(!(objDiscountRate.getStatus()));

            discountRateRepository.save(objDiscountRate);

        } else {
            throw new MyException("noExistDB", "discountRate");
        }
    }

    @Override
    public List<DiscountRateResponseDTO> findAllDiscountRatesByStatusTrue() {
        List<DiscountRate> discountRates = discountRateRepository.findAllByStatusTrue();
        ListValidator.validateResponseList(discountRates);

        return discountRateMapper.discountRatesListToRespList(discountRates);
    }

    @Override
    public List<DiscountRateResponseDTO> findAll() {
        List<DiscountRate> discountRates = discountRateRepository.findAll();
        ListValidator.validateResponseList(discountRates);

        return discountRateMapper.discountRatesListToRespList(discountRates);
    }

}
