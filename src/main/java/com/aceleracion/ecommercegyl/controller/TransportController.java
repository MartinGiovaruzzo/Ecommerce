package com.aceleracion.ecommercegyl.controller;

import com.aceleracion.ecommercegyl.dto.request.TransportRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.TransportResponseDTO;

import com.aceleracion.ecommercegyl.service.service.TransportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;
@CrossOrigin(origins = "http://gyl-ecommerce.s3-website-us-east-1.amazonaws.com/")
@RestController
@RequestMapping("/transport")
public class TransportController {

    private final TransportService transportService;

    public TransportController(TransportService transportService) {
        this.transportService = transportService;
    }

    @PostMapping("/create")
    @Operation(summary = "Crear un transporte")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transporte creado exitosamente",
                    content = @Content(schema = @Schema(implementation = TransportResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Solicitud de transporte inválida")})
    public ResponseEntity<TransportResponseDTO> createTransport(@RequestBody @Valid TransportRequestDTO transportRequest) {
        TransportResponseDTO objTransportResponse = transportService.createTransport(transportRequest);

        return new ResponseEntity<>(objTransportResponse, HttpStatus.CREATED);
    }

    @GetMapping("/")
    @Operation(summary = "Obtener todos los transportes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa. Devuelve todos los transportes encontrados",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = TransportResponseDTO.class)))),
            @ApiResponse(responseCode = "404", description = "No se encontraron transportes")})
    public ResponseEntity<List<TransportResponseDTO>> getAllTransports() {
            List<TransportResponseDTO> transports = transportService.findAllTransports();
            return ResponseEntity.ok(transports);
    }

    @GetMapping("/{transportId}")
    @Operation(summary = "Obtiene un transporte por su ID",
        parameters = {@Parameter(name = "transportId", description = "Id del transporte", example = "5")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa, transporte obtenido por su Id",
                content = @Content(schema = @Schema(implementation = TransportResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Transporte no encontrado")})
    public ResponseEntity<TransportResponseDTO> getTransportById(@PathVariable Long transportId) {
            TransportResponseDTO transportResponseDTO = transportService.findTransportById(transportId);
            return new ResponseEntity<>(transportResponseDTO, HttpStatus.OK);
    }

    @GetMapping("/date/{transportDate}")
    @Operation(summary = "Obtiene un transporte por fecha indicada",
            parameters = {@Parameter(name = "transportDate", description = "Fecha de transporte", example = "2023-06-29")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa, transporte obtenido por su fecha indicada",
                    content = @Content(schema = @Schema(implementation = TransportResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Transporte no encontrado")})
    public ResponseEntity<List<TransportResponseDTO>> getTransportDate(@PathVariable("transportDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String transportDate) throws ParseException {
            List<TransportResponseDTO> transportResponseDTOs = transportService.findTransportByDate(transportDate);
            return new ResponseEntity<>(transportResponseDTOs, HttpStatus.OK);
    }

    @GetMapping("/targetBranch/{branchId}")
    @Operation(summary = "Obtiene transportes por su sucursal de destino",
            parameters = {@Parameter(name = "branchId", description = "Id de la sucursal", example = "5")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa, transporte obtenido por su Id de sucursal de destino",
                    content = @Content(schema = @Schema(implementation = TransportResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Transporte no encontrado")})
    public ResponseEntity<List<TransportResponseDTO>> getTransportByTargetBranch(@PathVariable("branchId") Long branchId) {
            List<TransportResponseDTO> transportResponseDTOs = transportService.findTransportByTargetBranch(branchId);
            return new ResponseEntity<>(transportResponseDTOs, HttpStatus.OK);
    }

    @GetMapping("/originBranch/{branchId}")
    @Operation(summary = "Obtiene transportes por su sucursal de origen",
            parameters = {@Parameter(name = "branchId", description = "Id de la sucursal", example = "5")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa, transporte obtenido por su Id de sucursal de origen",
                    content = @Content(schema = @Schema(implementation = TransportResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Transporte no encontrado")})
    public ResponseEntity<List<TransportResponseDTO>> getTransportByOriginBranch(@PathVariable("branchId") Long branchId) {
            List<TransportResponseDTO> transportResponseDTOs = transportService.findTransportByOriginBranch(branchId);
            return new ResponseEntity<>(transportResponseDTOs, HttpStatus.OK);
    }

    @GetMapping("/originBranch/{branchId}/date/{date}")
    @Operation(summary = "obtener el transporte por sucursal de origen y fecha",
            parameters = {@Parameter(name = "branchId", description = "Id de la sucursal de origen", example = "5"),
                    @Parameter(name = "date", description = "Fecha indicada", example = "2023-06-29")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa, transporte obtenido por sucursal de origen y fecha",
                    content = @Content(schema = @Schema(implementation = TransportResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Transporte no encontrado")})
    public ResponseEntity<List<TransportResponseDTO>> getTransportOriginByBranchAndDate(
            @PathVariable("branchId") Long branchId, @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String date) throws ParseException {
            List<TransportResponseDTO> transportResponseDTOs = transportService.findTransportByOriginBranchDate(branchId, date);
            return new ResponseEntity<>(transportResponseDTOs, HttpStatus.OK);
        }


    @GetMapping("/targetBranch/{branchId}/date/{date}")
    @Operation(summary = "obtener el transporte por sucursal de destino y fecha",
            parameters = {@Parameter(name = "targetBranchId", description = "Id de la sucursal destino", example = "5"),
                    @Parameter(name = "date", description = "Fecha indicada", example = "2023-06-29")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa, transporte obtenido por sucursal de destino y fecha",
                    content = @Content(schema = @Schema(implementation = TransportResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Transporte no encontrado")})
    public ResponseEntity<List<TransportResponseDTO>> getTransportTargetByBranchAndDate(
            @PathVariable("branchId") Long targetBranchId, @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String date) throws ParseException {
            List<TransportResponseDTO> transportResponseDTOs = transportService.findTransportByTargetBranchDate(targetBranchId, date);
            return new ResponseEntity<>(transportResponseDTOs, HttpStatus.OK);
        }
    }



