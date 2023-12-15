package com.aceleracion.ecommercegyl.repository.serviceimpl;

import com.aceleracion.ecommercegyl.dto.mapper.InvoiceMapper;
import com.aceleracion.ecommercegyl.dto.mapper.ProductMapper;
import com.aceleracion.ecommercegyl.dto.request.InvoiceRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.InvoiceResponseDTO;
import com.aceleracion.ecommercegyl.dto.response.ProductResponseDTO;
import com.aceleracion.ecommercegyl.exception.MyException;
import com.aceleracion.ecommercegyl.model.*;
import com.aceleracion.ecommercegyl.repository.*;
import com.aceleracion.ecommercegyl.service.service.*;
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
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final InvoiceMapper invoiceMapper;
    private final BranchService branchService;
    private final CustomerService customerService;
    private final PaymentMethodService paymentMethodService;
    private final DiscountRateService discountRateService;
    private final InvoiceTypeService invoiceTypeService;
    private final UserService userService;
    private final ProductService productService;
    private final ProductMapper productMapper;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, InvoiceMapper invoiceMapper, BranchService branchService,
                              CustomerService customerService, PaymentMethodService paymentMethodService,
                              DiscountRateService discountRateService, InvoiceTypeService invoiceTypeService,
                              UserService userService, ProductService productService, ProductMapper productMapper) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceMapper = invoiceMapper;
        this.branchService = branchService;
        this.customerService = customerService;
        this.paymentMethodService = paymentMethodService;
        this.discountRateService = discountRateService;
        this.invoiceTypeService = invoiceTypeService;
        this.userService = userService;
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @Transactional
    @Override
    public InvoiceResponseDTO createInvoice(InvoiceRequestDTO invoiceRequestDTO) {

        if (invoiceRequestDTO != null) {
            Invoice objInvoice = new Invoice();
            Date date = new Date();
            objInvoice.setDate(date);

            Branch objBranch = branchService.findById(invoiceRequestDTO.getBranchId());
            objInvoice.setBranch(objBranch);

            Customer objCustomer = customerService.findByDni(invoiceRequestDTO.getCustomerDni());
            objInvoice.setCustomer(objCustomer);

            PaymentMethod objPaymentMethod = paymentMethodService.findById(invoiceRequestDTO.getPaymentMethodId());
            objInvoice.setPaymentMethod(objPaymentMethod);

            DiscountRate objDiscountRate = discountRateService.findById(invoiceRequestDTO.getDiscountRateId());
            objInvoice.setDiscountRate(objDiscountRate);

            InvoiceType invoiceType = invoiceTypeService.findById(invoiceRequestDTO.getInvoiceTypeId());
            objInvoice.setInvoiceType(invoiceType);

            User user = userService.findByDni(invoiceRequestDTO.getUserDni());
            objInvoice.setUser(user);

            List<Product> products = new ArrayList<>();

            for (Long aux : invoiceRequestDTO.getProductsListCode()) {

                Product objProduct = productService.findProductByCode(aux);
                objProduct.setStatus(false);
                productService.saveProduct(objProduct);
                products.add(objProduct);
            }

            objInvoice.setProductList(products);

            invoiceRepository.save(objInvoice);

            InvoiceResponseDTO invoiceRespDTO = invoiceMapper.invoiceToResp(objInvoice);

            List<ProductResponseDTO> productsResp = productMapper.productToRespList(products);

            invoiceRespDTO.setProductsResponseDTOList(productsResp);

            return invoiceRespDTO;

        } else {
            throw new MyException("entityNull", "invoiceRequest");
        }
    }

    @Override
    public InvoiceResponseDTO findInvoiceById(Long invoiceId) {
        if (invoiceId == null) {
            throw new MyException("entityNull", "invoiceId");
        }

        Optional<Invoice> optInvoice = invoiceRepository.findById(invoiceId);

        if (optInvoice.isPresent()) {
            Invoice objInvoice = optInvoice.get();

            List<ProductResponseDTO> productsResp = productMapper.productToRespList(objInvoice.getProductList());

            InvoiceResponseDTO objInvoiceResp = invoiceMapper.invoiceToResp(objInvoice);

            objInvoiceResp.setProductsResponseDTOList(productsResp);

            return objInvoiceResp;

        } else {
            throw new MyException("noExistDB", "invoice");
        }
    }

    @Override
    public Invoice findById(Long invoiceId) {
        if (invoiceId == null) {
            throw new MyException("entityNull", "invoiceId");
        }
        Optional<Invoice> objInvoice = invoiceRepository.findById(invoiceId);
        if (objInvoice.isPresent()) {
            return objInvoice.get();
        } else {
            throw new MyException("noExistDB", "invoice");
        }
    }

    @Override
    public List<InvoiceResponseDTO> findAllInvoices() {
        List<Invoice> invoices = invoiceRepository.findAll();
        ListValidator.validateResponseList(invoices);

        return mapAndSetProductsList(invoices);
    }

    @Override
    public List<InvoiceResponseDTO> findInvoicesByPaymentMethod(Long paymentMethodId) {
        if (paymentMethodId == null) {
            throw new MyException("entityNull", "paymentMethodId");
        }
        List<Invoice> invoices = invoiceRepository.findByPaymentMethodPaymentMethodId(paymentMethodId);
        ListValidator.validateResponseList(invoices);

        return mapAndSetProductsList(invoices);
    }

    @Override
    public List<InvoiceResponseDTO> findInvoicesByInvoiceType(Long invoiceTypeId) {
        if (invoiceTypeId == null) {
            throw new MyException("entityNull", "invoiceTypeId");
        }
        List<Invoice> invoices = invoiceRepository.findByPaymentMethodPaymentMethodId(invoiceTypeId);
        ListValidator.validateResponseList(invoices);

        return mapAndSetProductsList(invoices);
    }

    public List<InvoiceResponseDTO> findInvoicesByCustomerDni(String customerDni) {
        if (customerDni == null) {
            throw new MyException("entityNull", "customerDni");
        }
        List<Invoice> invoices = invoiceRepository.findInvoicesByCustomerDni(customerDni);
        ListValidator.validateResponseList(invoices);

        return mapAndSetProductsList(invoices);
    }

    public List<InvoiceResponseDTO> findInvoicesByUserDni(String userDni) {
        if (userDni == null) {
            throw new MyException("entityNull", "userDni");
        }
        List<Invoice> invoices = invoiceRepository.findInvoicesByUserDni(userDni);
        ListValidator.validateResponseList(invoices);

        return mapAndSetProductsList(invoices);
    }

    public List<InvoiceResponseDTO> findInvoicesByBranchBranchId(Long branchId) {
        if (branchId == null) {
            throw new MyException("entityNull", "branchId");
        }
        List<Invoice> invoices = invoiceRepository.findInvoicesByBranchBranchId(branchId);
        ListValidator.validateResponseList(invoices);

        return mapAndSetProductsList(invoices);
    }

    private List<InvoiceResponseDTO> mapAndSetProductsList(List<Invoice> invoices) {
        List<InvoiceResponseDTO> invoicesResp = new ArrayList<>();

        for (Invoice invoice : invoices) {
            List<ProductResponseDTO> productsResp = productMapper.productToRespList(invoice.getProductList());

            InvoiceResponseDTO objInvoiceResp = invoiceMapper.invoiceToResp(invoice);

            objInvoiceResp.setProductsResponseDTOList(productsResp);

            invoicesResp.add(objInvoiceResp);
        }
        ListValidator.validateResponseList(invoices);

        return invoicesResp;
    }

    @Override
    public List<InvoiceResponseDTO> findInvoicesByInvoiceTypesId() {
        List<Invoice> invoices = invoiceRepository.findInvoicesByInvoiceTypesId();
        ListValidator.validateResponseList(invoices);

        return mapAndSetProductsList(invoices);
    }

    @Override
    public List<InvoiceResponseDTO> invoiceReportByDate(String reportDate) throws ParseException {
        if (reportDate.isBlank()) {
            throw new MyException("entityNull", "reportDate");
        }

        List<Date> objDates = transformDate(reportDate);
        Date objStartDate = objDates.get(0);
        Date objEndDate = objDates.get(1);

        List<Invoice> objInvoices = invoiceRepository.findByDateBetween(objStartDate, objEndDate);
        ListValidator.validateResponseList(objInvoices);

        return mapAndSetProductsList(objInvoices);
    }

    private List<Date> transformDate(String invoiceDate) throws ParseException {

        SimpleDateFormat objDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = objDateFormat.parse(invoiceDate);

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
    public List<InvoiceResponseDTO> invoiceSell() {

        List<Invoice> objInvoices = invoiceRepository.findInvoicesByInvoiceTypesId();

        ListValidator.validateResponseList(objInvoices);

        return mapAndSetProductsList(objInvoices);
    }

}