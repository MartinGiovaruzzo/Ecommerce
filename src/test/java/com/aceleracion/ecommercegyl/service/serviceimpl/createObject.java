package com.aceleracion.ecommercegyl.service.serviceimpl;

import com.aceleracion.ecommercegyl.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class createObject {
   public static Product createPoductTest(){
       Product product = new Product();

       Brand brand = createBrandTest();

       product.setProductId(1L);
       product.setStatus(true);
       product.setProductCode(123L);
       product.setBrand(brand);
       product.setModel("abs");
       product.setSellingPrice(500.00);
       product.setPurchasePrice(700.00);
       product.setDescription("actualizado");


       return product;
   }
   public static Brand createBrandTest(){
       Brand brand = new Brand();

       brand.setBrandId(1L);
       brand.setName("Moto Moto");
       brand.setStatus(true);

       return brand;
   }
   public static Branch createBranchTest(){
       Branch branch = new Branch();

       List<Product> products = new ArrayList<>();
       Product product1 = createPoductTest();
       products.add(product1);
       Product product2 = createPoductTest();
       product2.setProductId(2L);
       product2.setProductCode(1234L);
       products.add(product2);

       Address address = createAddressTest();

       BranchType branchType = createBranchTypeTest();

       branch.setBranchId(1L);
       branch.setBranchName("Cotto");
       branch.setPhoneNumber("1231231233");
       branch.setStatus(true);
       branch.setAddress(address);
       branch.setBranchType(branchType);
       branch.setProducts(products);

       return branch;
   }
   public static Province createProvinceTest(){
       Province province = new Province();

       province.setProvinceId(1L);
       province.setProvinceName("Tucuman");
       province.setStatus(true);

       return province;
   }
   public static City createCityTest(){
       City city = new City();

       Province province = createProvinceTest();

       city.setCityId(1L);
       city.setCityName("San miguel de Tucuman");
       city.setStatus(true);
       city.setProvince(province);

       return city;
   }
   public static Address createAddressTest(){
       Address address = new Address();

       City city = createCityTest();

       address.setAddressId(1L);
       address.setStreet("San Martin");
       address.setNumber("123");
       address.setCity(city);
       address.setStatus(true);

       return address;
   }
   public static BranchType createBranchTypeTest() {
       BranchType branchType = new BranchType();

       branchType.setBranchTypeId(1L);
       branchType.setBranchType("Sucursal");
       branchType.setStatus(true);

       return branchType;
   }
   public static DiscountRate createDiscountRateTest() {
       DiscountRate discountRate = new DiscountRate();

       discountRate.setDiscountName("Generico");
       discountRate.setDiscountRateId(1L);
       discountRate.setDiscountPercentage(0.00);
       discountRate.setMaxDiscount(0.00);
       discountRate.setStatus(true);
       discountRate.setMinimumAmount(0.00);
       discountRate.setMaxAmount(0.00);

       return discountRate;
   }
   public static PaymentMethod createPaymentMethodTest() {
       PaymentMethod paymentMethod = new PaymentMethod();

       paymentMethod.setPaymentMethodId(1L);
       paymentMethod.setPaymentMethodName("Generico");
       paymentMethod.setStatus(true);

       return paymentMethod;
   }
   public static Role createRoleTest() {
       Role role = new Role();

       role.setRoleId(1L);
       role.setRoleName("Generico");
       role.setStatus(true);

       return role;
   }
   public static InvoiceType createInvoiceTypeTest(){
       InvoiceType invoiceType = new InvoiceType();

       invoiceType.setInvoiceTypeId(1L);
       invoiceType.setInvoiceName("Generico");
       invoiceType.setStatus(true);

       return invoiceType;
   }
   public static Transport createTransportTest() {
       Transport transport = new Transport();

       Branch branchOrigin = createBranchTest();
       Branch branchTarget = createBranchTest();
       branchTarget.setBranchId(2L);
       branchTarget.setBranchName("Pepito");

       List<Product> products = new ArrayList<>();
       Product product1 = createPoductTest();
       products.add(product1);
       Product product2 = createPoductTest();
       product2.setProductId(2L);
       product2.setProductCode(1234L);
       products.add(product2);

       transport.setTransportId(1L);
       transport.setDate(new Date());
       transport.setOriginBranch(branchOrigin);
       transport.setTargetBranch(branchTarget);
       transport.setProducts(products);

       return transport;
   }
   public static User createUserTest() {
       User user = new User();
       Role role = createRoleTest();
       Branch branch = createBranchTest();
       Address address = createAddressTest();

       user.setPersonId(1l);
       user.setDni("45645645");
       user.setName("Generico");
       user.setLastName("Generico");
       user.setEmail("generico@gmail.com");
       user.setBirthdate(LocalDate.parse("2002-06-01"));
       user.setPhoneNumber("1234567894");
       user.setStatus(true);
       user.setUserName("Marck");
       user.setPassword("xxxx");
       user.setAddress(address);
       user.setToken("XXXXXX");
       user.setRole(role);
       user.setBranch(branch);

       return user;
   }
   public static Customer createCustomerTest() {
       Customer customer = new Customer();

       Address address = createAddressTest();

       customer.setPersonId(1L);
       customer.setName("Generico");
       customer.setLastName("Generico");
       customer.setDni("45645646");
       customer.setEmail("generico@gmail.com");
       customer.setPhoneNumber("1234567895");
       customer.setBirthdate(LocalDate.parse("2002-06-01"));
       customer.setStatus(true);
       customer.setAddress(address);

       return customer;
   }
   public static Invoice createInvoiceTest(){
       Invoice invoice = new Invoice();

       Branch branch = createBranchTest();
       Customer customer = createCustomerTest();
       InvoiceType invoiceType = createInvoiceTypeTest();
       PaymentMethod paymentMethod = createPaymentMethodTest();
       User user = createUserTest();
       DiscountRate discountRate = createDiscountRateTest();

       List<Product> products = new ArrayList<>();
       Product product1 = createPoductTest();
       products.add(product1);
       Product product2 = createPoductTest();
       product2.setProductId(2L);
       product2.setProductCode(1234L);
       products.add(product2);

       invoice.setInvoiceId(1L);
       invoice.setDate(new Date());
       invoice.setBranch(branch);
       invoice.setCustomer(customer);
       invoice.setInvoiceType(invoiceType);
       invoice.setPaymentMethod(paymentMethod);
       invoice.setUser(user);
       invoice.setDiscountRate(discountRate);
       invoice.setProductList(products);

       return invoice;
   }
  /* public static BackSale createBackSaleTest() {
       BackSale backSale = new BackSale();

       Invoice invoiceSale = createInvoiceTest();
       Invoice invoiceBackSale = createInvoiceTest();
       invoiceBackSale.setInvoiceId(2L);

       backSale.setBackSaleId(1L);
       backSale.setDate(LocalDate.parse("2010-01-01"));
       backSale.setInvoiceSale(invoiceSale);
       backSale.setDescription("Generico");
       backSale.setInvoiceBackSale(invoiceBackSale);

       return backSale;
   }*/

}
