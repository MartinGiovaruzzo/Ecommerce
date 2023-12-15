package com.aceleracion.ecommercegyl.repository.serviceimpl;

import com.aceleracion.ecommercegyl.dto.mapper.BranchTypeMapper;
import com.aceleracion.ecommercegyl.dto.request.BranchTypeRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.BranchTypeResponseDTO;
import com.aceleracion.ecommercegyl.exception.MyException;
import com.aceleracion.ecommercegyl.model.BranchType;
import com.aceleracion.ecommercegyl.repository.BranchTypeRepository;
import com.aceleracion.ecommercegyl.service.service.BranchTypeService;
import com.aceleracion.ecommercegyl.utils.ListValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BranchTypeServiceImpl implements BranchTypeService {
    private final BranchTypeMapper branchTypeMapper;
    private final BranchTypeRepository branchTypeRepository;

    public BranchTypeServiceImpl(BranchTypeMapper branchTypeMapper, BranchTypeRepository branchTypeRepository) {
        this.branchTypeMapper = branchTypeMapper;
        this.branchTypeRepository = branchTypeRepository;
    }

    @Transactional
    @Override
    public BranchTypeResponseDTO createBranchType(BranchTypeRequestDTO branchTypeReqDTO)  {
        if (branchTypeReqDTO != null) {
            BranchType objActualBranchType = branchTypeRepository.findByBranchType(branchTypeReqDTO.getBranchType());
            if (objActualBranchType != null) {
                if (objActualBranchType.getStatus().equals(true)) {
                    throw new MyException("existDB", "branchType");
                } else {
                    throw new MyException("existDBStatus", "branchType");
                }
            }else {
                BranchType objBranchType = new BranchType();
                objBranchType.setBranchType(branchTypeReqDTO.getBranchType());
                objBranchType.setStatus(true);
                branchTypeRepository.save(objBranchType);
                return branchTypeMapper.branchTypeToResp(objBranchType);
            }
        }else{
            throw new MyException("entityNull", "branchTypeRequest");
        }
    }

    @Override
    public BranchTypeResponseDTO findBranchTypeById(Long branchTypeId) {
        if (branchTypeId==null){
            throw new MyException("entityNull", "branchTypeId");
        }
        BranchType objBranchType = findById(branchTypeId);
        return branchTypeMapper.branchTypeToResp(objBranchType);
    }

    @Override
    public BranchTypeResponseDTO findBranchTypeByName(String branchTypeName) {
        if (branchTypeName==null){
            throw new MyException("entityNull", "branchTypeName");
        }
        BranchType objBranchType = branchTypeRepository.findByBranchType(branchTypeName);

        if (objBranchType == null) {
            throw new MyException("noExistDB", "branchType");
        }
        return branchTypeMapper.branchTypeToResp(objBranchType);
    }

    @Override
    public List<BranchTypeResponseDTO> findAllBranchTypes() {
        List<BranchType> branchTypes = branchTypeRepository.findAll();
        ListValidator.validateResponseList(branchTypes);

        return branchTypeMapper.branchTypeToRespList(branchTypes);
    }

    @Override
    public BranchType findById(Long branchTypeId) {
        if (branchTypeId==null){
            throw new MyException("entityNull", "branchTypeId");
        }
        return branchTypeRepository.findById(branchTypeId)
                .orElseThrow(() -> new MyException("noExistDB", "branchType"));
    }

    @Override
    public void changeStatus(String branchType) {
        if (branchType==null){
            throw new MyException("entityNull", "branchType");
        }
        BranchType objBranchType = branchTypeRepository.findByBranchType(branchType);
        if (objBranchType == null) {
            throw new MyException("existDBStatus", "branchType");
        }
        boolean status=objBranchType.getStatus();

        objBranchType.setStatus(!status);

        branchTypeRepository.save(objBranchType);
    }

    @Override
    public List<BranchTypeResponseDTO> findAllByStatusTrue() {
        List<BranchType> branchTypes = branchTypeRepository.findAllByStatusTrue();
        ListValidator.validateResponseList(branchTypes);

        return branchTypeMapper.branchTypeToRespList(branchTypes);
    }


}
