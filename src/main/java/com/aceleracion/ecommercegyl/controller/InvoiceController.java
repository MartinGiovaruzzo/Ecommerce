package com.aceleracion.ecommercegyl.controller;


import com.aceleracion.ecommercegyl.dto.request.InvoiceRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.InvoiceResponseDTO;
import com.aceleracion.ecommercegyl.service.service.InvoiceService;
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
import javax.validation.constraints.NotNull;
import java.util.List;

@CrossOrigin(origins = "http://gyl-ecommerce.s3-website-us-east-1.amazonaws.com/")
@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @Operation(summary = "Crea un factura")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Factura creada correctamente",
                    content = @Content(schema = @Schema(implementation = InvoiceResponseDTO.class))),
            @ApiResponse(responseCode = "409", description = "La factura ya existe en el sistema")
    })
    @PostMapping("/create")
    public ResponseEntity<InvoiceResponseDTO> createInvoice(@RequestBody @Valid InvoiceRequestDTO invoiceRequestDTO) {
        InvoiceResponseDTO objInvoiceResponse = invoiceService.createInvoice(invoiceRequestDTO);

        return new ResponseEntity<>(objInvoiceResponse, HttpStatus.CREATED);
    }

    @Operation(summary = "Busca una factura por ID",
            parameters = {@Parameter(name = "invoiceId", description = "ID de la factura", example = "20")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa, factura encontrada por ID",
                    content = @Content(schema = @Schema(implementation = InvoiceResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Factura no encontrada")
    })
    @GetMapping("/{invoiceId}")
    public ResponseEntity<InvoiceResponseDTO> getInvoiceById(@PathVariable("invoiceId") Long invoiceId) {

            return new ResponseEntity<>(invoiceService.findInvoiceById(invoiceId), HttpStatus.OK);

    }

    @Operation(summary = "Obtiene todas las facturas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa. Devuelve todas las facturas encontradas",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = InvoiceResponseDTO.class)))),
            @ApiResponse(responseCode = "404", description = "No se encontraron facturas")
    })
    @GetMapping("/all")
    public ResponseEntity<List<InvoiceResponseDTO>> getAllInvoices() {
        return ResponseEntity.ok(invoiceService.findAllInvoices());
    }

    @Operation(summary = "Busca una factura por metodo de pago",
            parameters = {@Parameter(name = "paymentMethodId", description = "ID metodo de pago", example = "5")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa, factura encontrada por ID de metodo de pago",
                    content = @Content(schema = @Schema(implementation = InvoiceResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Factura no encontrada")
    })
    @GetMapping("/allbypaymentmethod/{paymentMethodId}")
    public ResponseEntity<List<InvoiceResponseDTO>> getAllInvoicesByPaymentMethod(@PathVariable("paymentMethodId") @NotNull Long paymentMethodId) {
        return ResponseEntity.ok(invoiceService.findInvoicesByPaymentMethod(paymentMethodId));
    }

    @Operation(summary = "Busca facturas por ID de tipo de factura",
            parameters = {@Parameter(name = "invoiceTypeId", description = "ID del tipo de factura", example = "1")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa, se encontraron facturas por ID de tipo de factura",
                    content = @Content(schema = @Schema(implementation = InvoiceResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Factura no encontrada")})
    @GetMapping("/allbyinvoicetype/{invoiceTypeId}")
    public ResponseEntity<List<InvoiceResponseDTO>> getAllInvoicesByInvoiceType(@PathVariable("invoiceTypeId") @NotNull Long invoiceTypeId) {
        return ResponseEntity.ok(invoiceService.findInvoicesByInvoiceType(invoiceTypeId));
    }

    @Operation(summary = "Genera un informe de facturas por fecha",
            parameters = {@Parameter(name = "reportDate", description = "Fecha del informe", example = "2023-06-26")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa, se generó el informe de facturas",
                    content = @Content(schema = @Schema(implementation = InvoiceResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Factura no encontrada")})
    @GetMapping("/allbydate/{reportDate}")
    public ResponseEntity<List<InvoiceResponseDTO>> invoiceReportByDate(@PathVariable("reportDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String reportDate) {
        try {
            List<InvoiceResponseDTO> invoiceResponseDTOs = invoiceService.invoiceReportByDate(reportDate);
            return new ResponseEntity<>(invoiceResponseDTOs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Busca facturas por ID de sucursal",
            parameters = {@Parameter(name = "branchId", description = "ID de la sucursal")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa, se encontraron facturas por ID de sucursal",
                    content = @Content(schema = @Schema(implementation = InvoiceResponseDTO.class)))})
    @GetMapping("/allbybranch/{branchId}")
    public ResponseEntity<List<InvoiceResponseDTO>> getAllInvoicesByBranch(@PathVariable("branchId") Long branchId) {
        return ResponseEntity.ok(invoiceService.findInvoicesByBranchBranchId(branchId));
    }

    @Operation(summary = "Busca facturas por DNI del cliente",
            parameters = {@Parameter(name = "customerDni", description = "DNI del cliente")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa, se encontraron facturas por DNI del cliente",
                    content = @Content(schema = @Schema(implementation = InvoiceResponseDTO.class)))})
    @GetMapping("/allbycustomer/{customerDni}")
    public ResponseEntity<List<InvoiceResponseDTO>> getAllInvoicesByCustomerDni(@PathVariable("customerDni") String customerDni) {
        return ResponseEntity.ok(invoiceService.findInvoicesByCustomerDni(customerDni));
    }

    @Operation(summary = "Busca facturas por DNI del usuario",
            parameters = {@Parameter(name = "userDni", description = "DNI del usuario")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa, se encontraron facturas por DNI del usuario",
                    content = @Content(schema = @Schema(implementation = InvoiceResponseDTO.class)))})
    @GetMapping("/allbyuser/{userDni}")
    public ResponseEntity<List<InvoiceResponseDTO>> getAllInvoicesByUserDni(@PathVariable("userDni") String userDni) {
        return ResponseEntity.ok(invoiceService.findInvoicesByUserDni(userDni));
    }


    @Operation(summary = "Busca facturas sin nota de credito")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa, se encontraron facturas por venta",
                    content = @Content(schema = @Schema(implementation = InvoiceResponseDTO.class)))})
    @GetMapping("/allbysell")
    public ResponseEntity<List<InvoiceResponseDTO>> getAllInvoiceSell() {
        return ResponseEntity.ok(invoiceService.findInvoicesByInvoiceTypesId());
    }
}
