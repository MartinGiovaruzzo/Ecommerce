package com.aceleracion.ecommercegyl.controller;

import com.aceleracion.ecommercegyl.dto.request.BranchTypeRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.BranchTypeResponseDTO;
import com.aceleracion.ecommercegyl.service.service.BranchTypeService;
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
@RequestMapping("/branchtype")
public class BranchTypeController {

    private final BranchTypeService branchTypeService;

    public BranchTypeController(BranchTypeService branchTypeService) {
        this.branchTypeService = branchTypeService;
    }

    @PostMapping("/create")
    @Operation(summary = "Crear un tipo de sucursal")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tipo de sucursal creado exitosamente",
                    content = @Content(schema = @Schema(implementation = BranchTypeResponseDTO.class))),
            @ApiResponse(responseCode = "409", description = "El tipo de sucursal ya existe en el sistema")})
    public ResponseEntity<BranchTypeResponseDTO> createBranchType(@RequestBody @Valid BranchTypeRequestDTO branchTypeReqDTO) {
        BranchTypeResponseDTO branchTypeRespDTO = branchTypeService.createBranchType(branchTypeReqDTO);

        return new ResponseEntity<>(branchTypeRespDTO, HttpStatus.CREATED);
    }

    @GetMapping("/name/{name}")
    @Operation(summary = "Obtener un tipo de sucursal por Nombre",
            parameters = {@Parameter(name = "branchType", description = "Nombre del tipo de sucursal", example = "Tienda")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa, tipo de sucursal obtenido por Nombre",
                    content = @Content(schema = @Schema(implementation = BranchTypeResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Tipo de sucursal no encontrada")})
    public ResponseEntity<BranchTypeResponseDTO> getBranchTypeByName(@PathVariable("name") String branchType) {
        return ResponseEntity.ok(branchTypeService.findBranchTypeByName(branchType));
    }

    @GetMapping("/all")
    @Operation(summary = "Obtener todos los TIPOS de sucursal")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa. Devuelve todos los tipos de sucursales encontrados",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = BranchTypeResponseDTO.class)))),
            @ApiResponse(responseCode = "404", description = "No se encontraron tipos de sucursales")})
    public ResponseEntity<List<BranchTypeResponseDTO>> getAllBranchTypes() {
        return ResponseEntity.ok(branchTypeService.findAllBranchTypes());
    }

    @PutMapping("/changestatus/{branchType}")
    @Operation(summary = "Cambiar estado de tipo de sucursal",
            description = "Este endpoint cambia el estado de un tipo de sucursal específico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estado de tipo de sucursal cambiado con éxito"),
            @ApiResponse(responseCode = "404", description = "Tipo de sucursal no encontrado")
    })
    public ResponseEntity<Void> changeBranchTypeStatus(@PathVariable("branchType") String branchType) {
        branchTypeService.changeStatus(branchType);

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping("/allstatustrue")
    @Operation(summary = "Obtener todos los tipos de sucursal con estado activo",
            description = "Este endpoint se utiliza para obtener todos los tipos de sucursal que tienen estado activo.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa. Devuelve todos los tipos de sucursal activos",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = BranchTypeResponseDTO.class)))),
            @ApiResponse(responseCode = "404", description = "No se encontraron tipos de sucursal activos")
    })
    public ResponseEntity<List<BranchTypeResponseDTO>> getAllBranchTypeByStatusTrue() {
        List<BranchTypeResponseDTO> branchTypeStatusTrue = branchTypeService.findAllByStatusTrue();
        return ResponseEntity.ok(branchTypeStatusTrue);
    }
}
