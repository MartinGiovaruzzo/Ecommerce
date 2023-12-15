package com.aceleracion.ecommercegyl.service.serviceimpl;

import com.aceleracion.ecommercegyl.dto.mapper.BackSaleMapper;
import com.aceleracion.ecommercegyl.dto.mapper.InvoiceMapper;
import com.aceleracion.ecommercegyl.dto.mapper.ProductMapper;
import com.aceleracion.ecommercegyl.dto.request.BackSaleRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.BackSaleResponseDTO;
import com.aceleracion.ecommercegyl.dto.response.InvoiceResponseDTO;
import com.aceleracion.ecommercegyl.model.*;
import com.aceleracion.ecommercegyl.repository.BackSaleRepository;
import com.aceleracion.ecommercegyl.repository.InvoiceRepository;
import com.aceleracion.ecommercegyl.repository.serviceimpl.BackSaleServiceImpl;
import com.aceleracion.ecommercegyl.service.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class BackSaleServiceImplTest {

    @Mock
    private BackSaleRepository backSaleRepository;

    @Mock
    private BackSaleMapper backSaleMapper;

    @Mock
    private InvoiceService invoiceService;

    @Mock
    private InvoiceTypeService invoiceTypeService;

    @Mock
    private UserService userService;

    @Mock
    private DiscountRateService discountRateService;

    @Mock
    private PaymentMethodService paymentMethodService;

    @Mock
    private InvoiceMapper invoiceMapper;

    @Mock
    private ProductMapper productMapper;

    @Mock
    private InvoiceRepository invoiceRepository;

    @Mock
    private ProductService productService;

    @InjectMocks
    private BackSaleServiceImpl backSaleService;

    /*@BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        backSaleService = new BackSaleServiceImpl(
                backSaleRepository,
                backSaleMapper,
                invoiceService,
                invoiceTypeService,
                userService,
                discountRateService,
                paymentMethodService,
                invoiceMapper,
                productMapper,
                invoiceRepository,
                productService
        );
    }*/

    @Test
    public void testCreateBackSale_ValidRequest_ReturnsBackSaleResponseDTO() {
        // Mocks
        BackSaleRequestDTO backSaleRequestDTO = new BackSaleRequestDTO();
        backSaleRequestDTO.setDescription("Back sale description");
        backSaleRequestDTO.setUserDni("1234567");
        backSaleRequestDTO.setProductsCode(Arrays.asList(1L, 2L, 3L));

        Invoice objInvoiceSale = new Invoice();
        objInvoiceSale.setInvoiceType(new InvoiceType());
        objInvoiceSale.setBranch(new Branch());
        objInvoiceSale.setCustomer(new Customer());
        objInvoiceSale.setDiscountRate(new DiscountRate());
        objInvoiceSale.setProductList(new ArrayList<>());

        Invoice objInvoiceBackSale = new Invoice();

        BackSale objBackSale = new BackSale();

        Product product1 = new Product();
        product1.setProductCode(1L);
        product1.setStatus(false);

        Product product2 = new Product();
        product2.setProductCode(2L);
        product2.setStatus(false);

        Product product3 = new Product();
        product3.setProductCode(3L);
        product3.setStatus(false);

        List<Product> productsInvoiceSale = Arrays.asList(product1, product2, product3);

        User user = new User();

        when(invoiceService.findById(backSaleRequestDTO.getInvoiceId())).thenReturn(objInvoiceSale);
        when(invoiceTypeService.findById(4L)).thenReturn(new InvoiceType());
        when(paymentMethodService.findById(1L)).thenReturn(new PaymentMethod());
        when(userService.findByDni(backSaleRequestDTO.getUserDni())).thenReturn(user);
        when(productService.findProductByCode(anyLong())).thenReturn(new Product());
        when(invoiceMapper.invoiceToResp(objInvoiceSale)).thenReturn(new InvoiceResponseDTO());
        when(invoiceMapper.invoiceToResp(objInvoiceBackSale)).thenReturn(new InvoiceResponseDTO());
        when(backSaleMapper.backSaleToResp(objBackSale)).thenReturn(new BackSaleResponseDTO());

        doNothing().when(productService).saveProduct(any(Product.class));

        BackSaleResponseDTO result = backSaleService.createBackSale(backSaleRequestDTO);

        assertNotNull(result);
    }



}