package com.aceleracion.ecommercegyl.service.service;

import com.aceleracion.ecommercegyl.dto.request.ProductRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.ProductResponseDTO;
import com.aceleracion.ecommercegyl.model.Product;
import java.util.List;

public interface ProductService {


    Product findById(Long productId);

    Product findProductByCode(Long productCode);

    ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) ;

    ProductResponseDTO findProductByCodeAndStatusTrue(Long productCode);
    List<ProductResponseDTO> findProductsByBranch(Long branchId);
    List<ProductResponseDTO> findAllProducts();

    List<ProductResponseDTO> findAllProductsByStatusTrue();

    List<Product> findProductsByBranchModel(Long branchId);

    List<ProductResponseDTO> findProductsByBranchAndProductType(Long branchId, Long productTypeId);

    List<ProductResponseDTO> findProductsByBranchAndModel(Long branchId, String model);

    ProductResponseDTO updateProduct(ProductRequestDTO productRequestDTO) ;

    void changeProductStatus(Long productCode);

    void saveProduct(Product product);
}
