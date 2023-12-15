package com.aceleracion.ecommercegyl.controller;

import com.aceleracion.ecommercegyl.dto.request.BranchRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.BranchResponseDTO;
import com.aceleracion.ecommercegyl.dto.response.BranchTypeResponseDTO;
import com.aceleracion.ecommercegyl.service.service.BranchService;
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
import javax.validation.constraints.NotNull;
import java.util.List;


@RestController

@RequestMapping("/branch")
@CrossOrigin(origins = "http://gyl-ecommerce.s3-website-us-east-1.amazonaws.com/")
public class BranchController {

    private final BranchService branchService;


    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @PostMapping("/create")
    @Operation(summary = "Crear una sucursal")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Sucursal creada exitosamente",
                    content = @Content(schema = @Schema(implementation = BranchTypeResponseDTO.class))),
            @ApiResponse(responseCode = "409", description = "La sucursal ya existe en el sistema")})
    public ResponseEntity<BranchResponseDTO> createBranch(@RequestBody @Valid BranchRequestDTO branchRequest) {
        BranchResponseDTO objBranchResponse = branchService.createBranch(branchRequest);

        return new ResponseEntity<>(objBranchResponse, HttpStatus.CREATED);
    }


    @GetMapping("/{branchId}")
    @Operation(summary = "Obtener una sucursal por ID",
            parameters = {@Parameter(name = "branchId", description = "ID de la sucursal", example = "20")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa, sucursal obtenida por ID",
                    content = @Content(schema = @Schema(implementation = BranchResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Sucursal no encontrada")})
    public ResponseEntity<BranchResponseDTO> getBranchById(@PathVariable("branchId") @NotNull Long branchId) {
        return new ResponseEntity<>(branchService.findBranchById(branchId),HttpStatus.OK);
    }


    @GetMapping("/all")
    @Operation(summary = "Obtener todas las sucursales")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa. Devuelve todas las sucursales encontradas",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = BranchResponseDTO.class)))),
            @ApiResponse(responseCode = "404", description = "No se encontraron sucursales")})
    public ResponseEntity<List<BranchResponseDTO>> getAllBranches() {
        return new ResponseEntity<>(branchService.findAllBranches(), HttpStatus.OK);
    }

    @GetMapping("/address")
    @Operation(summary = "Buscar una sucursal por dirección",
            parameters = {@Parameter(name = "street", description = "Nombre de la calle", example = "Calle Falsa"),
                    @Parameter(name = "number", description = "número de la calle", example = "123"),
                    @Parameter(name = "cityId", description = "ID de la ciudad", example = "10")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa, sucursal encontrada por dirección",
                    content = @Content(schema = @Schema(implementation = BranchResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Sucursal no encontrada")})
    public ResponseEntity<BranchResponseDTO> getBranchByAddress(
            @RequestParam("street") String street,
            @RequestParam("number") String number,
            @RequestParam("cityId") Long cityId) {

            BranchResponseDTO branchRespDTO = branchService.findBranchByAddress(street, number, cityId);
            return ResponseEntity.ok(branchRespDTO);

    }

    @GetMapping("/name/{branchName}")
    @Operation(summary = "Buscar sucursales por nombre",
            parameters = {@Parameter(name = "branchName", description = "Nombre de la sucursal", example = "Carrefour")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa, sucursales encontradas por nombre",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = BranchResponseDTO.class)))),
            @ApiResponse(responseCode = "404", description = "No se encontraron sucursales con el nombre especificado")})
    public ResponseEntity<List<BranchResponseDTO>> getBranchByName(@PathVariable String branchName) {
            return new ResponseEntity<>(branchService.findBranchByName(branchName), HttpStatus.OK);
    }


    @PutMapping("/update/{branchId}")
    @Operation(summary = "Actualizar una sucursal",
            parameters = {@Parameter(name = "branchId", description = "ID de la sucursal", example = "20")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa, sucursal actualizada",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = BranchResponseDTO.class)))),
            @ApiResponse(responseCode = "404", description = "Sucursal no encontrada")})
    public ResponseEntity<BranchResponseDTO> updateBranch(
            @PathVariable Long branchId,
            @RequestBody BranchRequestDTO branchRequestDTO) {
        BranchResponseDTO updatedBranch = branchService.updateBranch(branchId, branchRequestDTO);

        return ResponseEntity.ok(updatedBranch);
    }

    @PutMapping("/changestatus/{branchId}")
    @Operation(summary = "Cambiar el estado de una sucursal",
            parameters = {@Parameter(name = "branchId", description = "ID de la sucursal", example = "20")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa, estado de sucursal cambiado",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = BranchResponseDTO.class)))),
            @ApiResponse(responseCode = "404", description = "Sucursal no encontrada")})
    public ResponseEntity<Void> changeBranchStatusById(@PathVariable Long branchId) {
            branchService.changeStatus(branchId);
            return new ResponseEntity<>(HttpStatus.OK);
    }
    //@CrossOrigin(origins = "http://gyl-ecommerce.s3-website-us-east-1.amazonaws.com/")
    @GetMapping("/allstatustrue")
    @Operation(summary = "Obtener todas las sucursales con estado verdadero",
            description = "Este endpoint se utiliza para obtener todas las sucursales que tienen un estado verdadero.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa. Devuelve todas las sucursales con estado verdadero",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = BranchResponseDTO.class)))),
            @ApiResponse(responseCode = "404", description = "No se encontraron sucursales con estado verdadero")
    })
        public ResponseEntity<List<BranchResponseDTO>> getAllBranchByStatusTrue() {
        List<BranchResponseDTO> branchesStatusTrue = branchService.findAllBranchByStatusTrue();
        return ResponseEntity.ok(branchesStatusTrue);
    }

}


