package com.aceleracion.ecommercegyl.controller;

import com.aceleracion.ecommercegyl.dto.request.ProductRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.ProductResponseDTO;
import com.aceleracion.ecommercegyl.dto.response.ProductTypeResponseDTO;
import com.aceleracion.ecommercegyl.service.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://gyl-ecommerce.s3-website-us-east-1.amazonaws.com/")
@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping("/create")
    @Operation(summary = "Crear un producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Producto creado exitosamente",
                    content = @Content(schema = @Schema(implementation = ProductTypeResponseDTO.class))),
            @ApiResponse(responseCode = "409", description = "El producto ya existe en el sistema")})
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody @Valid ProductRequestDTO productRequest) {
        ProductResponseDTO objProductResponse = productService.createProduct(productRequest);

        return new ResponseEntity<>(objProductResponse, HttpStatus.CREATED);
    }


    @Operation(summary = "Actualiza un producto",
            parameters = {
                    @Parameter(name = "productCode", description = "Código del producto a actualizar")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa, producto actualizado",
                    content = @Content(schema = @Schema(implementation = ProductResponseDTO.class)))})
    @PutMapping("/update/{productCode}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable("productCode") Long productCode,
                                                            @RequestBody @Valid ProductRequestDTO productRequest) {
        productRequest.setProductCode(productCode);
        ProductResponseDTO updatedProduct = productService.updateProduct(productRequest);

        return ResponseEntity.ok(updatedProduct);
    }

    @GetMapping("/all")
    @Operation(summary = "Obtener todos los productos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa. Devuelve todos los productos encontrados",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProductResponseDTO.class)))),
            @ApiResponse(responseCode = "404", description = "No se encontraron productos")})
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        return ResponseEntity.ok(productService.findAllProducts());
    }

    @Operation(summary = "Obtiene todos los productos con estado activo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa, se obtienen los productos activos",
                    content = @Content(schema = @Schema(implementation = ProductResponseDTO.class)))})
    @GetMapping("/allbystatustrue")
    public ResponseEntity<List<ProductResponseDTO>> getAllProductsByStatusTrue() {
        return ResponseEntity.ok(productService.findAllProductsByStatusTrue());
    }

    @Operation(summary = "Obtiene un producto por su código",
            parameters = {@Parameter(name = "productCode", description = "Código del producto")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa, se obtiene el producto por su código",
                    content = @Content(schema = @Schema(implementation = ProductResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")})
    @GetMapping("/{productCode}")
    public ResponseEntity<ProductResponseDTO> getProductByCode(@PathVariable("productCode") Long productCode) {
        try {
            return ResponseEntity.ok(productService.findProductByCodeAndStatusTrue(productCode));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Obtiene todos los productos por ID de sucursal",
            parameters = {@Parameter(name = "branchId", description = "ID de la sucursal")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa, se obtienen los productos por ID de sucursal",
                    content = @Content(schema = @Schema(implementation = ProductResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Sucursal no encontrada")})
    @GetMapping("/branch/{branchId}")
    public ResponseEntity<List<ProductResponseDTO>> getAllProductsByBranch(@PathVariable Long branchId) {
        List<ProductResponseDTO> products = productService.findProductsByBranch(branchId);
        return ResponseEntity.ok(products);

    }


    @Operation(summary = "Obtiene productos por ID de sucursal y ID de tipo de producto",
            parameters = {
                    @Parameter(name = "branchId", description = "ID de la sucursal"),
                    @Parameter(name = "productTypeId", description = "ID del tipo de producto")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa, se obtienen los productos por ID de sucursal y ID de tipo de producto",
                    content = @Content(schema = @Schema(implementation = ProductResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Sucursal no encontrada o tipo de producto no encontrado")})
    @GetMapping("/branch/{branchId}/producttype/{productTypeId}")
    public ResponseEntity<List<ProductResponseDTO>> getProductsByBranchAndProductType(
            @PathVariable Long branchId, @PathVariable Long productTypeId) {

        List<ProductResponseDTO> products = productService.findProductsByBranchAndProductType(branchId, productTypeId);
        return ResponseEntity.ok(products);

    }

    @Operation(summary = "Obtiene productos por ID de sucursal y modelo",
            parameters = {
                    @Parameter(name = "branchId", description = "ID de la sucursal"),
                    @Parameter(name = "model", description = "Modelo de producto")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa, se obtienen los productos por ID de sucursal y modelo",
                    content = @Content(schema = @Schema(implementation = ProductResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Sucursal no encontrada o productos con el modelo especificado no encontrados")})
    @GetMapping("/branch/{branchId}/model/{model}")
    public ResponseEntity<List<ProductResponseDTO>> getProductsByBranchAndModel(
            @PathVariable Long branchId, @PathVariable String model) {

        List<ProductResponseDTO> products = productService.findProductsByBranchAndModel(branchId, model);
        return ResponseEntity.ok(products);

    }

    @Operation(summary = "Obtiene todos los productos por ID de sucursal y estado activo",
            parameters = {@Parameter(name = "branchId", description = "ID de la sucursal")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa, se obtienen todos los productos por ID de sucursal y estado activo",
                    content = @Content(schema = @Schema(implementation = ProductResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Sucursal no encontrada")})
    @GetMapping("/branchandstatustrue/{branchId}")
    public ResponseEntity<List<ProductResponseDTO>> getAllProductsByBranchAndStatusTrue(@PathVariable("branchId") Long branchId) {

        List<ProductResponseDTO> products = productService.findProductsByBranch(branchId);
        return ResponseEntity.ok(products);

    }

    @Operation(summary = "Cambia el estado de un producto por su código",
            parameters = {@Parameter(name = "productCode", description = "Código del producto")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa, se cambia el estado del producto"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")})
    @PutMapping("/changestatus/{productCode}")
    public ResponseEntity<Void> changeProductStatus(@PathVariable Long productCode) {

        productService.changeProductStatus(productCode);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}