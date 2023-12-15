package com.aceleracion.ecommercegyl.controller;

import com.aceleracion.ecommercegyl.dto.request.DiscountRateRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.DiscountRateResponseDTO;
import com.aceleracion.ecommercegyl.service.service.DiscountRateService;
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
@RequestMapping("/discountrate")
public class DiscountRateController {

    private final DiscountRateService discountRateService;

    public DiscountRateController(DiscountRateService discountRateService) {
        this.discountRateService = discountRateService;
    }

    @PostMapping("/create")
    @Operation(summary = "Crear una tasa de descuento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tasa de descuento creada exitosamente",
                    content = @Content(schema = @Schema(implementation = DiscountRateResponseDTO.class))),
            @ApiResponse(responseCode = "409", description = "La tasa de descuento ya existe en el sistema")})

    public ResponseEntity<DiscountRateResponseDTO> createDiscountRate(@Valid @RequestBody DiscountRateRequestDTO discountRateRequestDTO) {
        DiscountRateResponseDTO objDiscountResponse = discountRateService.createDiscountRate(discountRateRequestDTO);

        return new ResponseEntity<>(objDiscountResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{discountId}")
    @Operation(summary = "Obtener una tasa de descuento por ID",
            parameters = {@Parameter(name = "discountId", description = "ID de la tasa de descuento", example = "10")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa, tasa de descuento obtenida por ID",
                    content = @Content(schema = @Schema(implementation = DiscountRateResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Tasa de descuento no encontrada")})
    public ResponseEntity<DiscountRateResponseDTO> getDiscountRateById(@PathVariable("discountId") Long discountId) {
        DiscountRateResponseDTO responseDTO = discountRateService.findDiscountRateById(discountId);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/allstatustrue")
    @Operation(summary = "Obtener todas las tasas de descuento con estado activo",
            description = "Este endpoint devuelve todas las tasas de descuento que tienen un estado activo.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa, lista de tasas de descuento con estado activo",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = DiscountRateResponseDTO.class))))
    })

    public ResponseEntity<List<DiscountRateResponseDTO>> getAllDiscountRatesByStatusTrue() {
        return new ResponseEntity<>(discountRateService.findAllDiscountRatesByStatusTrue(), HttpStatus.OK);
    }

    @GetMapping("/all")
    @Operation(summary = "Obtener todas las tasas de descuento",
            description = "Este endpoint devuelve todas las tasas de descuento disponibles.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa, lista de todas las tasas de descuento",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = DiscountRateResponseDTO.class))))
    })
    public ResponseEntity<List<DiscountRateResponseDTO>> getAllDiscountRates() {
        return new ResponseEntity<>(discountRateService.findAll(), HttpStatus.OK);
    }

    @PutMapping("/changestatus/{discountRateId}")
    @Operation(summary = "Cambiar el estado de una tasa de descuento",
            description = "Este endpoint se utiliza para cambiar el estado de una tasa de descuento.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa, estado de la tasa de descuento cambiado"),
            @ApiResponse(responseCode = "404", description = "Tasa de descuento no encontrada")
    })
    public ResponseEntity<HttpStatus> changeStatus(@PathVariable("discountRateId") Long discountRateId) {

        discountRateService.changeStatus(discountRateId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
