package com.aceleracion.ecommercegyl.controller;

import com.aceleracion.ecommercegyl.dto.request.InvoiceTypeRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.InvoiceTypeResponseDTO;
import com.aceleracion.ecommercegyl.service.service.InvoiceTypeService;
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
@RequestMapping("/invoicetype")
public class InvoiceTypeController {
    private final InvoiceTypeService invoiceTypeService;

    public InvoiceTypeController(InvoiceTypeService invoiceTypeService) {
        this.invoiceTypeService = invoiceTypeService;
    }

    @PostMapping("/create")
    @Operation(summary = "Crear un tipo de factura")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tipo de factura creada exitosamente",
                    content = @Content(schema = @Schema(implementation = InvoiceTypeResponseDTO.class))),
            @ApiResponse(responseCode = "409", description = "El tipo de factura ya existe en el sistema")})
    public ResponseEntity<InvoiceTypeResponseDTO> createInvoiceType(@RequestBody @Valid InvoiceTypeRequestDTO invoiceTypeRequestDTO) {
        InvoiceTypeResponseDTO objInvoiceTypeResponse = invoiceTypeService.createInvoiceType(invoiceTypeRequestDTO);

        return new ResponseEntity<>(objInvoiceTypeResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{invoiceTypeId}")
    @Operation(summary = "Obtener un tipo de factura por ID",
            parameters = {@Parameter(name = "invoiceTypeId", description = "ID del tipo de tasa de descuento", example = "10")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa, tipo de factura obtenida por ID",
                    content = @Content(schema = @Schema(implementation = InvoiceTypeResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Tipo de factura no encontrada")})
    public ResponseEntity<InvoiceTypeResponseDTO> getInvoiceTypeById(@PathVariable("invoiceTypeId") Long invoiceTypeId) {
        return new ResponseEntity<>(invoiceTypeService.findInvoiceTypeById(invoiceTypeId), HttpStatus.OK);

    }

    @GetMapping("/all")
    @Operation(summary = "Obtener todos los tipos de factura")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa. Devuelve todos los tipos de factura encontradas",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = InvoiceTypeResponseDTO.class)))),
            @ApiResponse(responseCode = "404", description = "No se encontraron tipos de factura")})
    public ResponseEntity<List<InvoiceTypeResponseDTO>> getAllInvoiceTypes() {
        return new ResponseEntity<>(invoiceTypeService.findAllInvoiceTypes(), HttpStatus.OK);
    }

    @GetMapping("/allbystatustrue")
    @Operation(summary = "Obtener todos los tipos de factura activos",
            description = "devuelve todos los tipos de factura que se encuentran en estado activo.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa, lista de todos los tipos de factura activos",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = InvoiceTypeResponseDTO.class))))
    })
    public ResponseEntity<List<InvoiceTypeResponseDTO>> getAllInvoiceTypesByStatusTrue() {
        return new ResponseEntity<>(invoiceTypeService.findAllInvoiceTypesByStatusTrue(), HttpStatus.OK);
    }

    @PutMapping("/changestatus/{invoiceTypeId}")
    @Operation(summary = "Cambiar el estado de un tipo de factura",
            description = "Este endpoint se utiliza para cambiar el estado de un tipo de factura.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa, estado del tipo de factura cambiado"),
            @ApiResponse(responseCode = "404", description = "Tipo de factura no encontrado")
    })
    public ResponseEntity<HttpStatus> changeStatus(@PathVariable("invoiceTypeId") Long invoiceTypeId) {
            invoiceTypeService.changeStatus(invoiceTypeId);
            return new ResponseEntity<>(HttpStatus.OK);

    }
}
