package com.aceleracion.ecommercegyl.controller;

import com.aceleracion.ecommercegyl.dto.request.CityRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.CityResponseDTO;
import com.aceleracion.ecommercegyl.model.City;
import com.aceleracion.ecommercegyl.service.service.CityService;
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
@RequestMapping("/city")
public class CityController {
    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping("/create")
    @Operation(summary = "Crea una ciudad")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ciudad creada con exito",
                    content = @Content(schema = @Schema(implementation = CityResponseDTO.class))),
            @ApiResponse(responseCode = "409", description = "La ciudad ya existe en el sistema")
    })
    public ResponseEntity<CityResponseDTO> createCity(@RequestBody @Valid CityRequestDTO cityRequest) {
        CityResponseDTO objCityResponse = cityService.createCity(cityRequest);

        return new ResponseEntity<>(objCityResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{cityId}")
    @Operation(summary = "Obtiene una ciudad por su ID",
            parameters = {@Parameter(name = "cityId", description = "ID de la ciudad", example = "Mar del Plata")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Operación exitosa, ciudad obtenida por ID",
                    content = @Content(schema = @Schema(implementation = CityResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Ciudad no encontrada")})
    public ResponseEntity<City> getCityById(@PathVariable("cityId") Long cityId) {

        return new ResponseEntity<>(cityService.findCityById(cityId), HttpStatus.OK);

    }

    @GetMapping("/all")
    @Operation(summary = "Obtener todas las ciudades",
            description = "Este endpoint devuelve todas las ciudades disponibles.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa, lista de ciudades",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = CityResponseDTO.class))))
    })
    public ResponseEntity<List<CityResponseDTO>> getAllCities() {
        return ResponseEntity.ok(cityService.findAllCities());
    }

    @GetMapping("/allstatustrue")
    @Operation(summary = "Obtener todas las ciudades con estado activo",
            description = "Este endpoint devuelve todas las ciudades que tienen un estado activo.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa, lista de ciudades con estado activo",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = CityResponseDTO.class))))
    })
    public ResponseEntity<List<CityResponseDTO>> getAllCitiesByStatusTrue() {
        return ResponseEntity.ok(cityService.findAllCitiesByStatusTrue());
    }

    @PutMapping("/changestatus/{cityId}")
    @Operation(summary = "Cambiar el estado de una ciudad",
            parameters = {@Parameter(name = "cityId", description = "ID de la ciudad", example = "20")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa, estado de la ciudad cambiado"),
            @ApiResponse(responseCode = "404", description = "Ciudad no encontrada")
    })
    public ResponseEntity<Void> changeCityStatus(@PathVariable("cityId") Long cityId) {
        cityService.changeCityStatus(cityId);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
