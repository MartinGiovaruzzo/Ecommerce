package com.aceleracion.ecommercegyl.repository.serviceimpl;
import com.aceleracion.ecommercegyl.dto.mapper.TransportMapper;
import com.aceleracion.ecommercegyl.dto.request.TransportRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.TransportResponseDTO;
import com.aceleracion.ecommercegyl.exception.MyException;
import com.aceleracion.ecommercegyl.model.Branch;
import com.aceleracion.ecommercegyl.model.Product;
import com.aceleracion.ecommercegyl.model.Transport;
import com.aceleracion.ecommercegyl.repository.TransportRepository;
import com.aceleracion.ecommercegyl.service.service.BranchService;
import com.aceleracion.ecommercegyl.service.service.ProductService;
import com.aceleracion.ecommercegyl.service.service.TransportService;
import com.aceleracion.ecommercegyl.utils.ListValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TransportServiceImpl implements TransportService {

    private final TransportRepository transportRepository;
    private final TransportMapper transportMapper;
    private final BranchService branchService;
    private final ProductService productService;


    public TransportServiceImpl(TransportRepository transportRepository, TransportMapper transportMapper, BranchService branchService, ProductService productService) {
        this.transportRepository = transportRepository;
        this.transportMapper = transportMapper;
        this.branchService = branchService;
        this.productService = productService;
    }
    @Transactional
    @Override
    public TransportResponseDTO createTransport(TransportRequestDTO transportRequestDTO) throws MyException {

        if (transportRequestDTO!=null){
           Transport objTransport=null;
           List<Long> productsCode = transportRequestDTO.getProductsCode();
           List<Product> products = new ArrayList<>();
           Branch objOriginBranch = branchService.findById(transportRequestDTO.getOriginBranchId());
           Branch objTargetBranch = branchService.findById(transportRequestDTO.getTargetBranchId());
           List<Product> productsBranches = productService.findProductsByBranchModel(transportRequestDTO.getOriginBranchId());
              for (Long productCode : productsCode) {
                for (Product product : productsBranches) {

                   Long productRequestCode =productCode;
                   Long productOriginCode =product.getProductCode();

                    if (productRequestCode .equals(productOriginCode) ){
                        Product product1 =  productService.findProductByCode(productRequestCode);
                        product.setBranch(objTargetBranch);
                        products.add(product1);
                    }
                }

                if  (!products.isEmpty()){
                    objTransport = new Transport();
                    Date date = new Date();
                    objTransport.setDate(date);
                    objTransport.setOriginBranch(objOriginBranch);
                    objTransport.setTargetBranch(objTargetBranch);
                    objTransport.setProducts(products);

                }else{

                    throw new MyException("noExistDB", "transport");

                }
            }
            transportRepository.save(objTransport);
            return transportMapper.transportToResp(objTransport);
        }else{
            throw new MyException("entityNull", "transportRequest");
        }
    }

    @Override
    public TransportResponseDTO findTransportById(Long transportId) {
        if (transportId == null){
            throw new MyException("entityNull", "transportId");
        }

        Optional<Transport> objTransport = transportRepository.findById(transportId);
        if (objTransport.isPresent()) {
            return transportMapper.transportToResp(objTransport.get());
        }else{
            throw new MyException("noExistDB", "transport");
        }
    }

    @Override
    public List<TransportResponseDTO> findAllTransports() {
        List<Transport> transports = transportRepository.findAll();
        ListValidator.validateResponseList(transports);

        return transportMapper.transportToRespList(transports);
    }

    @Override
    public List<TransportResponseDTO> findTransportByDate(String transportDate) throws ParseException {
        if (transportDate.isBlank()){
            throw new MyException("entityNull", "transportDate");
        }

        List<Date> objDates = transformDate(transportDate);
        Date objStartDate = objDates.get(0);
        Date objEndDate = objDates.get(1);

        List<Transport> objTransports = transportRepository.findByDateBetween(objStartDate, objEndDate);
        return transportMapper.transportToRespList(objTransports);
    }

    private List<Date> transformDate(String transportDate) throws ParseException {

        SimpleDateFormat objDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = objDateFormat.parse(transportDate);

        LocalDate objLocalDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDateTime objStartDateTime = objLocalDate.atStartOfDay();
        LocalDateTime objEndDateTime = objLocalDate.atTime(LocalTime.MAX);
        List<Date> objDates = new ArrayList<>();
        Date objStartDate = Date.from(objStartDateTime.atZone(ZoneId.systemDefault()).toInstant());
        Date objEndDate = Date.from(objEndDateTime.atZone(ZoneId.systemDefault()).toInstant());
        objDates.add(objStartDate);
        objDates.add(objEndDate);

        return objDates;
    }

    @Override
    public List<TransportResponseDTO> findTransportByTargetBranch(Long branchId) {
        if (branchId == null){
            throw new MyException("entityNull", "branchId");
        }
        List<Transport> objTransports = transportRepository.findTransportByTargetBranchBranchId(branchId);
        ListValidator.validateResponseList(objTransports);

        return transportMapper.transportToRespList(objTransports);
    }


    @Override
    public List<TransportResponseDTO> findTransportByOriginBranch(Long branchId) {
        if (branchId == null){
            throw new MyException("entityNull", "branchId");
        }
        List<Transport> objTransports = transportRepository.findTransportByOriginBranchBranchId(branchId);
        ListValidator.validateResponseList(objTransports);

        return transportMapper.transportToRespList(objTransports);
    }

    @Override
    public List<TransportResponseDTO> findTransportByOriginBranchDate(Long branchId, String date) throws ParseException {
        if (branchId == null || date.isBlank()){
            throw new MyException("entityNull", "branchId o date");
        }
        List<Date> objDates = transformDate(date);
        Date startDate = objDates.get(0);
        Date endDate = objDates.get(1);

        List<Transport> objTransports = transportRepository.
                findTransportByOriginBranchBranchIdAndDateBetween(branchId,startDate,endDate);
        ListValidator.validateResponseList(objTransports);

        return transportMapper.transportToRespList(objTransports);
    }

    @Override
    public List<TransportResponseDTO> findTransportByTargetBranchDate(Long branchId, String date) throws ParseException {
        if (branchId == null || date.isBlank()){
            throw new MyException("entityNull", "branchId o date");
        }
        List<Date> objDates = transformDate(date);
        Date startDate = objDates.get(0);
        Date endDate = objDates.get(1);

        List<Transport> objTransports = transportRepository.findTransportByTargetBranchBranchIdAndDateBetween(branchId,startDate,endDate);
        ListValidator.validateResponseList(objTransports);

        return transportMapper.transportToRespList(objTransports);
    }

}

