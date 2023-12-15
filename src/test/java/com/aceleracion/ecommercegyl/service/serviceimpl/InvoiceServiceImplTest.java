package com.aceleracion.ecommercegyl.service.serviceimpl;

import com.aceleracion.ecommercegyl.dto.mapper.InvoiceMapper;
import com.aceleracion.ecommercegyl.dto.mapper.ProductMapper;
import com.aceleracion.ecommercegyl.dto.request.InvoiceRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.*;
import com.aceleracion.ecommercegyl.model.*;
import com.aceleracion.ecommercegyl.repository.InvoiceRepository;
import com.aceleracion.ecommercegyl.repository.serviceimpl.InvoiceServiceImpl;
import com.aceleracion.ecommercegyl.service.service.*;
import com.aceleracion.ecommercegyl.utils.ListValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;

import java.time.LocalDate;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class InvoiceServiceImplTest {
    @InjectMocks
    private InvoiceServiceImpl invoiceService;
    @Mock
    private InvoiceRepository invoiceRepository;
    @Mock
    private InvoiceMapper invoiceMapper;
    @Mock
    private BranchService branchService;
    @Mock
    private CustomerService customerService;
    @Mock
    private PaymentMethodService paymentMethodService;
    @Mock
    private DiscountRateService discountRateService;
    @Mock
    private InvoiceTypeService invoiceTypeService;
    @Mock
    private UserService userService;
    @Mock
    private ProductService productService;
    @Mock
    private ProductMapper productMapper;
    @Mock
    private ListValidator listValidator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        invoiceService = new InvoiceServiceImpl(invoiceRepository, invoiceMapper, branchService,
                customerService, paymentMethodService, discountRateService, invoiceTypeService, userService, productService, productMapper);
    }

    @Test
    void createInvoice2() {
        Product product1 = createObject.createPoductTest();
        product1.setProductCode(666L);
        Product product2 = createObject.createPoductTest();
        product2.setProductCode(777L);
        Product product3 = createObject.createPoductTest();
        product3.setProductCode(888L);

        InvoiceRequestDTO invoiceRequestDTO = new InvoiceRequestDTO();
        invoiceRequestDTO.setBranchId(1L);
        invoiceRequestDTO.setCustomerDni("123456789");
        invoiceRequestDTO.setPaymentMethodId(1L);
        invoiceRequestDTO.setDiscountRateId(1L);
        invoiceRequestDTO.setInvoiceTypeId(1L);
        invoiceRequestDTO.setUserDni("987654321");
        invoiceRequestDTO.setProductsListCode(Arrays.asList(product1.getProductCode(), product2.getProductCode(), product3.getProductCode()));

        Branch objBranch = createObject.createBranchTest();
        Mockito.when(branchService.findById(Mockito.anyLong())).thenReturn(objBranch);

        Customer objCustomer = createObject.createCustomerTest();
        Mockito.when(customerService.findByDni(Mockito.anyString())).thenReturn(objCustomer);

        PaymentMethod objPaymentMethod = createObject.createPaymentMethodTest();
        Mockito.when(paymentMethodService.findById(Mockito.anyLong())).thenReturn(objPaymentMethod);

        DiscountRate objDiscountRate = createObject.createDiscountRateTest();
        Mockito.when(discountRateService.findById(Mockito.anyLong())).thenReturn(objDiscountRate);

        InvoiceType invoiceType = createObject.createInvoiceTypeTest();
        Mockito.when(invoiceTypeService.findById(Mockito.anyLong())).thenReturn(invoiceType);

        User user = createObject.createUserTest();
        Mockito.when(userService.findByDni(Mockito.anyString())).thenReturn(user);

        Mockito.when(productService.findProductByCode(Mockito.anyLong())).thenAnswer((InvocationOnMock invocation) -> {
            Long productCode = invocation.getArgument(0);
            Product product = createObject.createPoductTest();
            product.setProductCode(productCode);
            return product;
        });
        Mockito.when(productService.findById(Mockito.anyLong())).thenAnswer((InvocationOnMock invocation) -> {
            Long productId = invocation.getArgument(0);
            Product product = createObject.createPoductTest();
            product.setProductId(productId);
            product.setProductCode(productId + 443L);
            return product;
        });

        InvoiceResponseDTO invoiceResponseDTO = new InvoiceResponseDTO();
        invoiceResponseDTO.setInvoiceId(1L);
        invoiceResponseDTO.setDate(LocalDate.parse("2000-06-01"));
        invoiceResponseDTO.setBranchResponseDTO(new BranchResponseDTO());
        invoiceResponseDTO.setCustomerResponseDTO(new CustomerResponseDTO());
        invoiceResponseDTO.setInvoiceTypeResponseDTO(new InvoiceTypeResponseDTO());
        invoiceResponseDTO.setPaymentMethodResponseDTO(new PaymentMethodResponseDTO());
        invoiceResponseDTO.setUserResponseDTO(new User());
        invoiceResponseDTO.setDiscountRateResponseDTO(new DiscountRateResponseDTO());
        invoiceResponseDTO.setProductsResponseDTOList(new ArrayList<>());

        List<ProductResponseDTO> productsResponseDTOList = new ArrayList<>();
        List<Long> productListCode = Arrays.asList(product1.getProductCode(), product2.getProductCode(), product3.getProductCode());

        for (Long productCode : productListCode) {
            ProductResponseDTO productResponseDTO = new ProductResponseDTO();
            productResponseDTO.setProductCode(productCode);
            productResponseDTO.setModel("abc");
            productResponseDTO.setStatus(true);
            productResponseDTO.setPurchasePrice(500.00);
            productResponseDTO.setSellingPrice(700.00);
            productResponseDTO.setProductTypeResponseDTO(new ProductTypeResponseDTO());
            productResponseDTO.setBranchResponseDTO(new BranchResponseDTO(1L, "Cotto", new BranchTypeResponseDTO(1L, "Sucursal", true),
                    "4561234565", true, new AddressResponseDTO("manuelita", "11", new CityResponseDTO(1L, "Pehuajo", true, new ProvinceResponseDTO(1L, "Tucuman", true)))));

            productsResponseDTOList.add(productResponseDTO);
        }

        invoiceResponseDTO.setProductsResponseDTOList(productsResponseDTOList);

        Mockito.when(invoiceMapper.invoiceToResp(Mockito.any(Invoice.class))).thenReturn(invoiceResponseDTO);

        InvoiceResponseDTO result = invoiceService.createInvoice(invoiceRequestDTO);

        assertNotNull(result);
        Mockito.verify(invoiceRepository, Mockito.times(1)).save(Mockito.any(Invoice.class));
    }

    @Test
    void testFindById_ValidInvoiceId_ReturnsInvoice() {

        Long invoiceId = 1L;

        Invoice invoice = createObject.createInvoiceTest();

        Mockito.when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(invoice));

        Invoice result = invoiceService.findById(invoiceId);

        Mockito.verify(invoiceRepository, Mockito.times(1)).findById(invoiceId);

        assertNotNull(result);
        assertEquals(invoice, result);
    }
}

