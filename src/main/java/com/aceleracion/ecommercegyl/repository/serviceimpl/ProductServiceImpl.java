package com.aceleracion.ecommercegyl.repository.serviceimpl;

import com.aceleracion.ecommercegyl.dto.mapper.ProductMapper;
import com.aceleracion.ecommercegyl.dto.request.ProductRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.ProductResponseDTO;
import com.aceleracion.ecommercegyl.exception.MyException;
import com.aceleracion.ecommercegyl.model.Branch;
import com.aceleracion.ecommercegyl.model.Brand;
import com.aceleracion.ecommercegyl.model.Product;
import com.aceleracion.ecommercegyl.model.ProductType;
import com.aceleracion.ecommercegyl.repository.ProductRepository;
import com.aceleracion.ecommercegyl.service.service.BranchService;
import com.aceleracion.ecommercegyl.service.service.BrandService;
import com.aceleracion.ecommercegyl.service.service.ProductService;
import com.aceleracion.ecommercegyl.service.service.ProductTypeService;
import com.aceleracion.ecommercegyl.utils.ListValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    private final BrandService brandService;
    private final ProductTypeService productTypeService;
    private final BranchService branchService;


    public ProductServiceImpl(ProductMapper productMapper, ProductRepository productRepository, BrandService brandService,
                              ProductTypeService productTypeService, BranchService branchService) {
        this.productMapper = productMapper;
        this.productRepository = productRepository;
        this.brandService = brandService;
        this.productTypeService = productTypeService;
        this.branchService = branchService;
    }



    @Transactional
    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO)  {
        if (productRequestDTO == null) {
            throw new MyException("entityNull", "productRequest");
        }

                Brand objBrand = brandService.findBrandById(productRequestDTO.getBrandId());
                ProductType objProductType = productTypeService.findById(productRequestDTO.getProductTypeId());
                Branch objBranch = branchService.findById(productRequestDTO.getBranchId());
                Product objProduct = productMapper.reqToProduct(productRequestDTO);

                objProduct.setBrand(objBrand);
                objProduct.setProductType(objProductType);
                objProduct.setBranch(objBranch);
                objProduct.setStatus(true);
                productRepository.save(objProduct);
                objBranch.getProducts().add(objProduct);
                branchService.saveBranch(objBranch);
                return productMapper.productToResp(objProduct);
            }

    @Transactional
    @Override
    public ProductResponseDTO updateProduct(ProductRequestDTO productRequestDTO)  {
        if (productRequestDTO == null) {
            throw new MyException("entityNull", "productRequest");
        }

        Long productCode = productRequestDTO.getProductCode();
        Product existingProduct = productRepository.findByProductCodeAndStatusTrue(productCode);
        if (existingProduct == null) {
            throw new MyException("noExistDB", "product");
        }

        Brand objBrand = brandService.findBrandById(productRequestDTO.getBrandId());
        ProductType objProductType = productTypeService.findById(productRequestDTO.getProductTypeId());

        existingProduct.setBrand(objBrand);
        existingProduct.setProductType(objProductType);
        existingProduct.setModel(productRequestDTO.getModel());
        existingProduct.setSellingPrice(productRequestDTO.getSellingPrice());
        existingProduct.setPurchasePrice(productRequestDTO.getPurchasePrice());
        existingProduct.setDescription(productRequestDTO.getDescription());
        productRepository.save(existingProduct);
        return productMapper.productToResp(existingProduct);
    }

    @Override
    public ProductResponseDTO findProductByCodeAndStatusTrue(Long productCode) {
        if (productCode == null) {
            throw new MyException("entityNull", "productCode");
        }
        Product objProduct = productRepository.findByProductCodeAndStatusTrue(productCode);

        if (objProduct != null) {
            return productMapper.productToResp(objProduct);
        } else {
            throw new MyException("noExistDB", "product");
        }
    }

    @Override
    public List<ProductResponseDTO> findProductsByBranch(Long branchId) {
        if (branchId == null) {
            throw new MyException("entityNull", "branchId");
        }

        List<Product> productList = productRepository.findAllByBranchBranchIdAndStatusTrue(branchId);
        return productMapper.productToRespList(productList);
    }


    @Override
    public List<Product> findProductsByBranchModel(Long branchId) {
        if (branchId == null) {
            throw new MyException("entityNull", "branchId");
        }
        List<Product> products = productRepository.findAllByBranchBranchIdAndStatusTrue(branchId);
        ListValidator.validateResponseList(products);

        return products;
    }

    @Override
    public List<ProductResponseDTO> findProductsByBranchAndProductType(Long branchId, Long productTypeId) {
        if (branchId == null || productTypeId == null) {
            throw new MyException("entityNull", "branchId o productTypeId");
        }

        List<Product> productList = productRepository.findAllByBranchBranchIdAndProductTypeProductTypeIdAndStatusTrue(branchId, productTypeId);
        ListValidator.validateResponseList(productList);

        return productMapper.productToRespList(productList);
    }

    @Override
    public List<ProductResponseDTO> findProductsByBranchAndModel(Long branchId, String model) {
        if (branchId == null || model.isBlank()) {
            throw new MyException("entityNull", "branchId o model");
        }

        List<Product> productList = productRepository.findAllByBranchBranchIdAndModelAndStatusTrue(branchId, model);
        ListValidator.validateResponseList(productList);

        return productMapper.productToRespList(productList);
    }

    @Override
    public List<ProductResponseDTO> findAllProducts() {
        List<Product> products = productRepository.findAll();
        ListValidator.validateResponseList(products);

        return productMapper.productToRespList(products);
    }

    @Override
    public List<ProductResponseDTO> findAllProductsByStatusTrue() {
        List<Product> products = productRepository.findAllByStatusTrue();
        ListValidator.validateResponseList(products);

        return productMapper.productToRespList(products);
    }

    @Override
    public Product findById(Long productId) {
        if (productId == null) {
            throw new MyException("entityNull", "productId");
        }
        return productRepository.findById(productId)
                .orElseThrow(() -> new MyException("noExistDB", "product"));
    }

    @Override
    public Product findProductByCode(Long productCode) {
        if (productCode == null) {
            throw new MyException("entityNull", "productCode");
        }
        Product objProduct = productRepository.findByProductCode(productCode);

        if (objProduct == null) {
            throw new MyException("noExistDB", "product");
        }
        return objProduct;
    }

    @Override
    public void changeProductStatus(Long productCode) {
        if (productCode == null) {
            throw new MyException("noExistDB", "productCode");
        }

        Product objProduct = productRepository.findByProductCode(productCode);
        objProduct.setStatus(!objProduct.getStatus());
        productRepository.save(objProduct);
    }

    @Override
    public void saveProduct(Product product) {

    }


}

