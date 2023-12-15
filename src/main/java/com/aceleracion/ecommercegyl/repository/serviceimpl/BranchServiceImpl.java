package com.aceleracion.ecommercegyl.repository.serviceimpl;

import com.aceleracion.ecommercegyl.dto.mapper.AddressMapper;
import com.aceleracion.ecommercegyl.dto.mapper.BranchMapper;
import com.aceleracion.ecommercegyl.dto.mapper.CityMapper;
import com.aceleracion.ecommercegyl.dto.request.AddressRequestDTO;
import com.aceleracion.ecommercegyl.dto.request.BranchRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.AddressResponseDTO;
import com.aceleracion.ecommercegyl.dto.response.BranchResponseDTO;
import com.aceleracion.ecommercegyl.dto.response.CityResponseDTO;
import com.aceleracion.ecommercegyl.exception.MyException;
import com.aceleracion.ecommercegyl.model.Address;
import com.aceleracion.ecommercegyl.model.Branch;
import com.aceleracion.ecommercegyl.model.BranchType;
import com.aceleracion.ecommercegyl.repository.BranchRepository;
import com.aceleracion.ecommercegyl.service.service.AddressService;
import com.aceleracion.ecommercegyl.service.service.BranchService;
import com.aceleracion.ecommercegyl.service.service.BranchTypeService;
import com.aceleracion.ecommercegyl.utils.ListValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class BranchServiceImpl implements BranchService {
    private final BranchMapper branchMapper;
    private final BranchRepository branchRepository;
    private final AddressService addressService;
    private final AddressMapper addressMapper;
    private final BranchTypeService branchTypeService;

    private final CityMapper cityMapper;

    public static final String DB_NO_EXIST = "noExistDB";


    public BranchServiceImpl(BranchMapper branchMapper, BranchRepository branchRepository, AddressService addressService,
                             AddressMapper addressMapper, BranchTypeService branchTypeService, CityMapper cityMapper) {
        this.branchMapper = branchMapper;
        this.branchRepository = branchRepository;
        this.addressService = addressService;
        this.addressMapper = addressMapper;
        this.branchTypeService = branchTypeService;
        this.cityMapper = cityMapper;
    }

    @Transactional
    @Override
    public BranchResponseDTO createBranch(BranchRequestDTO branchRequestDTO) {
        if (branchRequestDTO == null) {
            throw new MyException("entityNull", "branchRequest");
        }
        AddressRequestDTO addressReqDTO = branchRequestDTO.getAddressReqDTO();
        Branch objActualBranch = branchRepository.findByAddress(addressReqDTO.getStreet(),
                addressReqDTO.getNumber(), addressReqDTO.getCityId());

        BranchType objBranchType = branchTypeService.findById(branchRequestDTO.getBranchTypeId());

        if (objActualBranch != null) {
            throw new MyException("ExistBranchAddress", "branch");
        }
        Address objAddress = addressService.createAddress(addressReqDTO.getStreet(), addressReqDTO.getNumber(), addressReqDTO.getCityId());
        Branch objBranch = branchMapper.reqToBranch(branchRequestDTO);
        objBranch.setAddress(objAddress);
        objBranch.setBranchType(objBranchType);
        objBranch.setStatus(true);
        branchRepository.save(objBranch);
        BranchResponseDTO branchResponseDTO = branchMapper.branchToResp(objBranch);
        branchResponseDTO.setBranchTypeResponseDTO(branchTypeService.findBranchTypeById(objBranchType.getBranchTypeId()));
        CityResponseDTO cityResponseDTO = cityMapper.cityToResp(objAddress.getCity());
        AddressResponseDTO addressResponseDTO = addressMapper.addressToResp(objAddress);
        addressResponseDTO.setCityResponseDTO(cityResponseDTO);
        branchResponseDTO.setAddressResponseDTO(addressMapper.addressToResp(objAddress));

        return branchResponseDTO;
    }

    @Override
    public BranchResponseDTO findBranchById(Long branchId) {
        if (branchId == null) {
            throw new MyException("entityNull", "branchId");
        }
        Branch objBranch = branchRepository.findByBranchIdAndStatus(branchId, true);
        if (objBranch == null) {
            throw new MyException("noExistDB", "branch");
        }
        return branchMapper.branchToResp(objBranch);
    }

    @Override
    public Branch findById(Long branchId) {
        if (branchId == null) {
            throw new MyException("entityNull", "branchId");
        }
        Branch objBranch = branchRepository.findByBranchIdAndStatus(branchId, true);
        if (objBranch == null) {
            throw new MyException("noExistDB", "branch");
        }
            return objBranch;
    }

    @Override
    public List<BranchResponseDTO> findBranchByName(String branchName) {
        if (branchName == null) {
            throw new MyException("entityNull", "branchName");
        }

        List<Branch> branches = branchRepository.findBranchByBranchName(branchName);
        if (branches.isEmpty()) {
            throw new MyException("noDataFound");
        }
        ListValidator.validateResponseList(branches);

        return branchMapper.branchToRespList(branches);
    }

    @Override
    public BranchResponseDTO findBranchByAddress(String street, String number, Long cityId) {
        if (street.isBlank() || number.isBlank() || cityId == null) {
            throw new MyException("entityNull", "street, number o cityId");
        }
        Branch objBranch = branchRepository.findByAddress(street, number, cityId);
        return branchMapper.branchToResp(objBranch);
    }

    @Override
    public List<BranchResponseDTO> findAllBranches() {
        List<Branch> branches = branchRepository.findAllByStatus(true);
        ListValidator.validateResponseList(branches);

        return branchMapper.branchToRespList(branches);
    }

    @Transactional
    @Override
    public BranchResponseDTO updateBranch(Long branchId, BranchRequestDTO branchRequestDTO)  {
        Branch objActualBranch = branchRepository.findById(branchId)
                .orElseThrow(() -> new MyException("noExistDB", "branch"));

        if (branchRequestDTO.getAddressReqDTO() != null) {
            AddressRequestDTO addressReqDTO = branchRequestDTO.getAddressReqDTO();
            Address objAddress = addressService.createAddress(
                    addressReqDTO.getStreet(),
                    addressReqDTO.getNumber(),
                    addressReqDTO.getCityId()
            );
            objActualBranch.setAddress(objAddress);
        }

        if (branchRequestDTO.getBranchTypeId() != null) {
            BranchType objBranchType = branchTypeService.findById(branchRequestDTO.getBranchTypeId());
            objActualBranch.setBranchType(objBranchType);
        }

        objActualBranch.setBranchName(branchRequestDTO.getBranchName());

        branchRepository.save(objActualBranch);

        BranchResponseDTO branchResponseDTO = branchMapper.branchToResp(objActualBranch);
        branchResponseDTO.setBranchTypeResponseDTO(branchTypeService.findBranchTypeById(objActualBranch.getBranchType().getBranchTypeId()));
        CityResponseDTO cityResponseDTO = cityMapper.cityToResp(objActualBranch.getAddress().getCity());
        AddressResponseDTO addressResponseDTO = addressMapper.addressToResp(objActualBranch.getAddress());
        addressResponseDTO.setCityResponseDTO(cityResponseDTO);
        branchResponseDTO.setAddressResponseDTO(addressResponseDTO);

        return branchResponseDTO;
    }

    @Override
    public void changeStatus(Long branchId) {
        if (branchId == null) {
            throw new MyException("entityNull", "branchId");
        }
        Optional<Branch> optionalBranch = branchRepository.findById(branchId);

        if (optionalBranch.isPresent()) {

            Branch objBranch = optionalBranch.get();
            boolean status = objBranch.getStatus();

            objBranch.setStatus(!status);

            branchRepository.save(objBranch);
        } else {
            throw new MyException("noExistDB", "branch");
        }

    }

    @Override
    public List<BranchResponseDTO> findAllBranchByStatusTrue() {
        List<Branch> branchesStatusTrue = branchRepository.findAllBranchByStatusTrue();
        ListValidator.validateResponseList(branchesStatusTrue);

        return branchMapper.branchToRespList(branchesStatusTrue);
    }

    @Override
    public void saveBranch(Branch branch) {
        branchRepository.save(branch);
    }

}