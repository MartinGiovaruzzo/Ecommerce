package com.aceleracion.ecommercegyl.repository.serviceimpl;

import com.aceleracion.ecommercegyl.dto.mapper.InvoiceTypeMapper;
import com.aceleracion.ecommercegyl.dto.request.InvoiceTypeRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.InvoiceTypeResponseDTO;
import com.aceleracion.ecommercegyl.exception.MyException;
import com.aceleracion.ecommercegyl.model.InvoiceType;
import com.aceleracion.ecommercegyl.repository.InvoiceTypeRepository;
import com.aceleracion.ecommercegyl.service.service.InvoiceTypeService;
import com.aceleracion.ecommercegyl.utils.ListValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceTypeServiceImpl implements InvoiceTypeService {
    private final InvoiceTypeRepository invoiceTypeRepository;
    private final InvoiceTypeMapper invoiceTypeMapper;

    public InvoiceTypeServiceImpl(InvoiceTypeRepository invoiceTypeRepository, InvoiceTypeMapper invoiceTypeMapper) {
        this.invoiceTypeRepository = invoiceTypeRepository;
        this.invoiceTypeMapper = invoiceTypeMapper;
    }

    @Transactional
    @Override
    public InvoiceTypeResponseDTO createInvoiceType(InvoiceTypeRequestDTO invoiceTypeRequestDTO) {
        if (invoiceTypeRequestDTO != null) {
            InvoiceType invoiceType = invoiceTypeMapper.reqToInvType(invoiceTypeRequestDTO);
            if ((invoiceTypeRepository.findInvoiceTypeByInvoiceName(invoiceType.getInvoiceName()) != null)) {
                throw new MyException("existDB", "invoiceType");
            }
            invoiceType.setStatus(true);
            invoiceTypeRepository.save(invoiceType);
            return invoiceTypeMapper.invTypeToResp(invoiceType);
        } else {
            throw new MyException("entityNull", "invoiceTypeRequest");
        }
    }

    @Override
    public InvoiceTypeResponseDTO findInvoiceTypeById(Long invoiceTypeId) {
        if (invoiceTypeId == null) {
            throw new MyException("entityNull", "invoiceTypeId");
        }
        Optional<InvoiceType> invoiceType = invoiceTypeRepository.findById(invoiceTypeId);
        if (invoiceType.isPresent()) {
            return invoiceTypeMapper.invTypeToResp(invoiceType.get());
        } else {
            throw new MyException("noExistDB", "invoiceType");
        }
    }

    @Override
    public InvoiceType findById(Long invoiceTypeId) {
        if (invoiceTypeId == null) {
            throw new MyException("entityNull", "invoiceTypeId");
        }
        Optional<InvoiceType> invoiceType = invoiceTypeRepository.findById(invoiceTypeId);
        if (invoiceType.isPresent()) {
            return invoiceType.get();
        } else {
            throw new MyException("noExistDB", "invoiceType");
        }
    }

    @Override
    public List<InvoiceTypeResponseDTO> findAllInvoiceTypes() {
        List<InvoiceType> invoiceTypes = invoiceTypeRepository.findAll();
        ListValidator.validateResponseList(invoiceTypes);

        return invoiceTypeMapper.invTypeToRespList(invoiceTypes);
    }

    @Override
    public List<InvoiceTypeResponseDTO> findAllInvoiceTypesByStatusTrue() {
        List<InvoiceType> invoiceTypes = invoiceTypeRepository.findAllByStatusTrue();
        ListValidator.validateResponseList(invoiceTypes);

        return invoiceTypeMapper.invTypeToRespList(invoiceTypeRepository.findAllByStatusTrue());
    }

    @Override
    public void changeStatus(Long invoiceTypeId) {
        if (invoiceTypeId == null) {
            throw new MyException("entityNull", "invoiceTypeId");
        }
        Optional<InvoiceType> optionalInvoiceType = invoiceTypeRepository.findById(invoiceTypeId);

        if (optionalInvoiceType.isPresent()) {

            InvoiceType objInvoiceType = optionalInvoiceType.get();

            objInvoiceType.setStatus(!(objInvoiceType.getStatus()));

            invoiceTypeRepository.save(objInvoiceType);

        } else {
            throw new MyException("noExistDB", "invoiceType");
        }
    }
}
