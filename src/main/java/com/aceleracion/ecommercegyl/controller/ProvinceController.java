package com.aceleracion.ecommercegyl.controller;

import com.aceleracion.ecommercegyl.dto.request.ProvinceRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.ProvinceResponseDTO;
import com.aceleracion.ecommercegyl.service.service.ProvinceService;
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
@RequestMapping("/province")
public class ProvinceController {

    private final ProvinceService provinceService;

    public ProvinceController(ProvinceService provinceService) {
        this.provinceService = provinceService;
    }

    @PostMapping("/create")
    @Operation(summary = "Crear una provincia")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Provincia creada exitosamente",
                    content = @Content(schema = @Schema(implementation = ProvinceResponseDTO.class))),
            @ApiResponse(responseCode = "409", description = "La provincia ya existe en el sistema")})
    public ResponseEntity<ProvinceResponseDTO> createProvince(@RequestBody @Valid ProvinceRequestDTO provinceRequestDTO) {
        ProvinceResponseDTO objProvinceResponse = provinceService.createProvince(provinceRequestDTO);

        return new ResponseEntity<>(objProvinceResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{provinceId}")
    @Operation(summary = "Obtener una provincia por ID",
            parameters = {@Parameter(name = "provinceId", description = "ID de la provincia", example = "20")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa, provincia obtenida por ID",
                    content = @Content(schema = @Schema(implementation = ProvinceResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Provincia no encontrada")})
    public ResponseEntity<ProvinceResponseDTO> getProvinceById(@PathVariable("provinceId") Long provinceId) {

            return new ResponseEntity<>(provinceService.findProvinceById(provinceId), HttpStatus.OK);

    }

    @GetMapping("/all")
    @Operation(summary = "Obtener todas las provincias",
            description = "Este endpoint se utiliza para obtener todas las provincias.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa. Devuelve todas las provincias",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProvinceResponseDTO.class))))
    })
    public ResponseEntity<List<ProvinceResponseDTO>> getAllProvinces() {
        return new ResponseEntity<>(provinceService.findAllProvinces(), HttpStatus.OK);
    }

    @PutMapping("/changestatus/{provinceId}")
    @Operation(summary = "Cambiar el estado de una provincia",
            description = "Este endpoint se utiliza para cambiar el estado de una provincia.",
            parameters = {@Parameter(name = "provinceId", description = "ID de la provincia", example = "20")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa. El estado de la provincia ha sido cambiado"),
            @ApiResponse(responseCode = "404", description = "Provincia no encontrada")
    })
    public ResponseEntity<ProvinceResponseDTO> changeProvinceStatusById(@PathVariable("provinceId") Long provinceId) {
            provinceService.changeStatus(provinceId);
            return new ResponseEntity<>(HttpStatus.OK);

    }
}