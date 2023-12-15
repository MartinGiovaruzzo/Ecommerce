package com.aceleracion.ecommercegyl.repository.serviceimpl;

import com.aceleracion.ecommercegyl.dto.mapper.BackSaleMapper;
import com.aceleracion.ecommercegyl.dto.mapper.InvoiceMapper;
import com.aceleracion.ecommercegyl.dto.mapper.ProductMapper;
import com.aceleracion.ecommercegyl.dto.request.BackSaleRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.BackSaleResponseDTO;
import com.aceleracion.ecommercegyl.dto.response.InvoiceResponseDTO;
import com.aceleracion.ecommercegyl.dto.response.ProductResponseDTO;
import com.aceleracion.ecommercegyl.exception.MyException;
import com.aceleracion.ecommercegyl.model.BackSale;
import com.aceleracion.ecommercegyl.model.Invoice;
import com.aceleracion.ecommercegyl.model.Product;
import com.aceleracion.ecommercegyl.repository.BackSaleRepository;
import com.aceleracion.ecommercegyl.repository.InvoiceRepository;
import com.aceleracion.ecommercegyl.service.service.*;
import com.aceleracion.ecommercegyl.utils.ListValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BackSaleServiceImpl implements BackSaleService {
    private final BackSaleRepository backSaleRepository;
    private final BackSaleMapper backSaleMapper;
    private final InvoiceService invoiceService;
    private final InvoiceTypeService invoiceTypeService;
    private final UserService userService;
    private final PaymentMethodService paymentMethodService;
    private final InvoiceMapper invoiceMapper;
    private final ProductMapper productMapper;
    private final InvoiceRepository invoiceRepository;
    private final ProductService productService;

    public BackSaleServiceImpl(BackSaleRepository backSaleRepository, BackSaleMapper backSaleMapper, InvoiceService invoiceService,
                               InvoiceTypeService invoiceTypeService, UserService userService,
                               PaymentMethodService paymentMethodService, InvoiceMapper invoiceMapper, ProductMapper productMapper,
                               InvoiceRepository invoiceRepository, ProductService productService) {
        this.backSaleRepository = backSaleRepository;
        this.backSaleMapper = backSaleMapper;
        this.invoiceService = invoiceService;
        this.invoiceTypeService = invoiceTypeService;
        this.userService = userService;
        this.paymentMethodService = paymentMethodService;
        this.invoiceMapper = invoiceMapper;
        this.productMapper = productMapper;
        this.invoiceRepository = invoiceRepository;
        this.productService = productService;
    }

    @Transactional
    @Override
    public BackSaleResponseDTO createBackSale(BackSaleRequestDTO backSaleRequestDTO) {
        if (backSaleRequestDTO != null) {
            Invoice objInvoiceSale = invoiceService.findById(backSaleRequestDTO.getInvoiceId());


            if (objInvoiceSale.getInvoiceType().getInvoiceTypeId() < 4) {

                BackSale objBackSale = new BackSale();

                objBackSale.setDate(LocalDate.now());

                objBackSale.setDescription(backSaleRequestDTO.getDescription());

                Invoice objInvoiceBackSale = new Invoice();

                objInvoiceBackSale.setDate(new Date());
                objInvoiceBackSale.setBranch(objInvoiceSale.getBranch());
                objInvoiceBackSale.setCustomer(objInvoiceSale.getCustomer());
                objInvoiceBackSale.setInvoiceType(invoiceTypeService.findById(4L));
                objInvoiceBackSale.setPaymentMethod(paymentMethodService.findById(1L));
                objInvoiceBackSale.setDiscountRate(objInvoiceSale.getDiscountRate());
                objInvoiceBackSale.setUser(userService.findByDni(backSaleRequestDTO.getUserDni()));

                List<Product> productsInvoiceSale = objInvoiceSale.getProductList();

                List<Long> productsId = backSaleRequestDTO.getProductsCode();

                List<Product> auxSaleList = new ArrayList<>(productsInvoiceSale);
                List<Product> auxBackSaleList = new ArrayList<>();

                for (Product product : productsInvoiceSale) {
                    for (Long productCode : productsId) {
                        if (product.getProductCode().equals(productCode)) {

                            Product auxProduct = productService.findProductByCode(productCode);
                            auxProduct.setStatus(true);
                            productService.saveProduct(auxProduct);
                            auxBackSaleList.add(product);

                        }
                    }
                }

                objInvoiceSale.setProductList(auxSaleList);
                objInvoiceBackSale.setProductList(auxBackSaleList);

                invoiceRepository.save(objInvoiceSale);
                invoiceRepository.save(objInvoiceBackSale);

                objBackSale.setInvoiceSale(objInvoiceSale);
                objBackSale.setInvoiceBackSale(objInvoiceBackSale);

                backSaleRepository.save(objBackSale);

                return mapAndSetInvoicesIntoBackSale(objInvoiceSale, objInvoiceBackSale, objBackSale);
            } else {
                throw new MyException("NotValid", "invoiceType");

            }

        } else {
            throw new MyException("entityNull", "backSale");
        }
    }

    @Override
    public BackSaleResponseDTO findBackSaleById(Long backSaleId) {
        if (backSaleId == null) {
            throw new MyException("entityNull", "backSaleId");
        }

        Optional<BackSale> objOpBackSale = backSaleRepository.findById(backSaleId);

        if (objOpBackSale.isPresent()) {

            BackSale objBackSale = objOpBackSale.get();

            Invoice invoiceSale = objBackSale.getInvoiceSale();
            Invoice invoiceBackSale = objBackSale.getInvoiceBackSale();

            return mapAndSetInvoicesIntoBackSale(invoiceSale, invoiceBackSale, objBackSale);

        } else {
            throw new MyException("noExistDB", "backSale");
        }
    }

    @Override
    public List<BackSaleResponseDTO> findAllBackSales() {
        List <BackSale> backSales = backSaleRepository.findAll();
        List <BackSaleResponseDTO> backSalesResp = new ArrayList<>();

        for (BackSale backSale : backSales){
            backSalesResp.add(mapAndSetInvoicesIntoBackSale(backSale.getInvoiceSale(), backSale.getInvoiceBackSale(), backSale));
        }
        ListValidator.validateResponseList(backSalesResp);

        return backSalesResp;
    }

    private BackSaleResponseDTO mapAndSetInvoicesIntoBackSale(Invoice invoiceSale, Invoice invoiceBackSale, BackSale backSale) {
        List<Product> productsSale = invoiceSale.getProductList();
        List<Product> productsBackSale = invoiceBackSale.getProductList();

        List<ProductResponseDTO> productsSaleResp = productMapper.productToRespList(productsSale);
        List<ProductResponseDTO> productsBackSaleResp = productMapper.productToRespList(productsBackSale);

        InvoiceResponseDTO objInvoiceSaleResp = invoiceMapper.invoiceToResp(invoiceSale);
        InvoiceResponseDTO objInvoiceBackSaleResp = invoiceMapper.invoiceToResp(invoiceBackSale);

        objInvoiceSaleResp.setProductsResponseDTOList(productsSaleResp);
        objInvoiceBackSaleResp.setProductsResponseDTOList(productsBackSaleResp);

        BackSaleResponseDTO backSaleResp = backSaleMapper.backSaleToResp(backSale);

        backSaleResp.setInvoiceSaleResponseDTO(objInvoiceSaleResp);
        backSaleResp.setInvoiceBackSaleResponseDTO(objInvoiceBackSaleResp);

        return backSaleResp;
    }

}
