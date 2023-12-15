package com.aceleracion.ecommercegyl.controller;

import com.aceleracion.ecommercegyl.dto.request.BackSaleRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.BackSaleResponseDTO;
import com.aceleracion.ecommercegyl.service.service.BackSaleService;
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
@RequestMapping("/backsale")
public class BackSaleController {

    private final BackSaleService backSaleService;

    public BackSaleController(BackSaleService backSaleService) {
        this.backSaleService = backSaleService;
    }

    @PostMapping("/create")
    @Operation(summary = "Crea una devolución")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Devolución creada exitosamente",
                    content = @Content(schema = @Schema(implementation = BackSaleResponseDTO.class))),
            @ApiResponse(responseCode = "409", description = "La devolución ya existe en el sistema")})
    public ResponseEntity<BackSaleResponseDTO> createBackSale(@RequestBody @Valid BackSaleRequestDTO backSaleRequestDTO) {
        BackSaleResponseDTO objBackSaleResponse = backSaleService.createBackSale(backSaleRequestDTO);

        return new ResponseEntity<>(objBackSaleResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{backSaleId}")
    @Operation(summary = "Obtiene una devolución por su ID",
            parameters = {@Parameter(name = "backSaleId", description = "ID de la devolución", example = "20")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Operación exitosa, devolución obtenida por ID",
                    content = @Content(schema = @Schema(implementation = BackSaleResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "devolución no encontrada")})
    public ResponseEntity<BackSaleResponseDTO> getBackSaleById(@PathVariable Long backSaleId) {

        return new ResponseEntity<>(backSaleService.findBackSaleById(backSaleId), HttpStatus.OK);
    }

    @GetMapping("/all")
    @Operation(summary = "Obtener todos las devoluciones")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Operación exitosa. Devuelve todas las devoluciones encontradas",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = BackSaleResponseDTO.class)))),
            @ApiResponse(responseCode = "404", description = "No se encontro ninguna devolución")})
    public ResponseEntity<List<BackSaleResponseDTO>> getAllBackSales() {

        List<BackSaleResponseDTO> backSaleResponseDTOS = backSaleService.findAllBackSales();
        return new ResponseEntity<>(backSaleResponseDTOS, HttpStatus.OK);

    }

}
