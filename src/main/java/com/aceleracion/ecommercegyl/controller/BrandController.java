package com.aceleracion.ecommercegyl.controller;

import com.aceleracion.ecommercegyl.dto.request.BrandRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.BrandResponseDTO;
import com.aceleracion.ecommercegyl.model.Brand;
import com.aceleracion.ecommercegyl.service.service.BrandService;
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
@RequestMapping("/brand")
public class BrandController {

    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }


    @PostMapping("/create")
    @Operation(summary = "Crea una marca")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Marca creada exitosamente",
                    content = @Content(schema = @Schema(implementation = BrandResponseDTO.class))),
            @ApiResponse(responseCode = "409", description = "La marca ya se encuentra en el sistema")})
    public ResponseEntity<BrandResponseDTO> createBrand(@RequestBody @Valid BrandRequestDTO brandRequest) {
        BrandResponseDTO objBrandResponse = brandService.createBrand(brandRequest);

        return new ResponseEntity<>(objBrandResponse, HttpStatus.CREATED);
    }

    @GetMapping("/name/{name}")
    @Operation(summary = "Actualizar una marca",
            description = "Este endpoint actualiza una marca existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Marca actualizada con éxito",
                    content = @Content(schema = @Schema(implementation = BrandResponseDTO.class)))
    })
    public ResponseEntity<BrandResponseDTO> updateBrand(@PathVariable("name") String name,
                                                                           @RequestBody @Valid BrandRequestDTO brandRequestDTO) {
        BrandResponseDTO updatedBrand = brandService.updateBrand(name, brandRequestDTO);

        return ResponseEntity.ok(updatedBrand);
    }

    @GetMapping("/{brandId}")
    @Operation(summary = "Obtiene una marca por ID",
            parameters = {@Parameter(name = "brandId", description = "ID de la marca", example = "Samsung")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Marca creada exitosamente",
                    content = @Content(schema = @Schema(implementation = BrandResponseDTO.class))),
            @ApiResponse(responseCode = "409", description = "La marca ya existe en el sistema")})
    public ResponseEntity<Brand> getBrandById(@PathVariable("brandId") Long brandId) {
        return ResponseEntity.ok(brandService.findBrandById(brandId));
    }

    @GetMapping("/all")
    @Operation(summary = "Obtiene todas las marcas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa. Devuelve todas las marcas encontradas",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = BrandResponseDTO.class)))),
            @ApiResponse(responseCode = "404", description = "No se encontraron marcas")
    })
    public ResponseEntity<List<BrandResponseDTO>> getAllBrands() {
        return ResponseEntity.ok(brandService.findAllBrands());
    }

    @PutMapping("/changestatus/{name}")
    @Operation(summary = "Cambiar el estado de una marca por nombre",
            description = "Este endpoint cambia el estado de una marca específica por su nombre.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estado de la marca cambiado con éxito"),
            @ApiResponse(responseCode = "404", description = "Marca no encontrada")
    })
    public ResponseEntity<Void> changeBrandStatusByName(@PathVariable("name") String name) {

            brandService.changeStatus(name);
            return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping("/allstatustrue")
    @Operation(summary = "Obtener todas las marcas con estado activo",
            description = "Este endpoint devuelve todas las marcas que tienen un estado activo.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa, lista de marcas con estado activo",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = BrandResponseDTO.class))))
    })
    public ResponseEntity<List<BrandResponseDTO>> getAllBrandByStatusTrue() {
        List<BrandResponseDTO> brandsStatusTrue = brandService.findAllBrandByStatusTrue();
        return ResponseEntity.ok(brandsStatusTrue);
    }
}
