package com.aceleracion.ecommercegyl.controller;

import com.aceleracion.ecommercegyl.dto.request.ProductTypeRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.ProductTypeResponseDTO;
import com.aceleracion.ecommercegyl.service.service.ProductTypeService;
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
@RequestMapping("/producttype")
public class ProductTypeController {

    private final ProductTypeService productTypeService;

    public ProductTypeController(ProductTypeService productTypeService) {
        this.productTypeService = productTypeService;
    }

    @PostMapping("/create")
    @Operation(summary = "Crear un tipo de producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tipo de producto creado exitosamente",
                    content = @Content(schema = @Schema(implementation = ProductTypeResponseDTO.class))),
            @ApiResponse(responseCode = "409", description = "El tipo de producto ya existe en el sistema")})
    public ResponseEntity<ProductTypeResponseDTO> createProductType(@RequestBody @Valid ProductTypeRequestDTO productTypeRequest) {
        ProductTypeResponseDTO objProductTypeResponse = productTypeService.createProductType(productTypeRequest);

        return new ResponseEntity<>(objProductTypeResponse, HttpStatus.CREATED);
    }

    @GetMapping("/name/{productType}")
    @Operation(summary = "Obtener un tipo de producto por Nombre",
            parameters = {@Parameter(name = "productType", description = "Nombre del tipo de producto", example = "Electronica")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa, tipo de producto obtenido por Nombre",
                    content = @Content(schema = @Schema(implementation = ProductTypeResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Tipo de producto no encontrado")})
    public ResponseEntity<ProductTypeResponseDTO> getProductTypeByName(@PathVariable("productType") String productType) {
        return ResponseEntity.ok(productTypeService.findProductTypeByName(productType));
    }

    @GetMapping("/")
    @Operation(summary = "Obtener todos los tipos de productos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa. Devuelve todos los tipos de productos encontrados",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProductTypeResponseDTO.class)))),
            @ApiResponse(responseCode = "404", description = "No se encontraron tipos de productos")})
    public ResponseEntity<List<ProductTypeResponseDTO>> getAllProductTypes() {
        return ResponseEntity.ok(productTypeService.findAllProductTypes());
    }


    @PutMapping("/changestatus/{productTypeId}")
    @Operation(summary = "Cambiar el estado de un tipo de producto por ID",
            description = "Este endpoint se utiliza para cambiar el estado de un tipo de producto según su ID.",
            parameters = {@Parameter(name = "productTypeId", description = "ID del tipo de producto", example = "1")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa. El estado del tipo de producto ha sido cambiado"),
            @ApiResponse(responseCode = "404", description = "Tipo de producto no encontrado")
    })
    public ResponseEntity<Void> changeProductTypeStatusById(@PathVariable("productTypeId") Long productTypeId) {

            productTypeService.changeStatus(productTypeId);
            return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping("/allbystatustrue")
    @Operation(summary = "Obtener todos los tipos de producto activos",
            description = "Este endpoint se utiliza para obtener todos los tipos de producto que están activos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa. Devuelve todos los tipos de producto activos",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProductTypeResponseDTO.class)))),
            @ApiResponse(responseCode = "404", description = "No se encontraron tipos de producto activos")
    })
    public ResponseEntity<List<ProductTypeResponseDTO>> getAllProductTypeByStatusTrue() {
        List<ProductTypeResponseDTO> productTypesStatusTrue = productTypeService.findAllProductTypeByStatusTrue();
        return ResponseEntity.ok(productTypesStatusTrue);
    }

}
