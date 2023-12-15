package com.aceleracion.ecommercegyl.controller;

import com.aceleracion.ecommercegyl.dto.request.PaymentMethodRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.PaymentMethodResponseDTO;
import com.aceleracion.ecommercegyl.model.PaymentMethod;
import com.aceleracion.ecommercegyl.service.service.PaymentMethodService;
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
@RequestMapping("/paymentmethod")
public class PaymentMethodController {

    private final PaymentMethodService paymentMethodService;

    public PaymentMethodController(PaymentMethodService paymentMethodService) {
        this.paymentMethodService = paymentMethodService;
    }

    @PostMapping("/create")
    @Operation(summary = "Crear un método de pago")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Método de pago creado exitosamente",
                    content = @Content(schema = @Schema(implementation = PaymentMethodResponseDTO.class))),
            @ApiResponse(responseCode = "409", description = "El método de pago ya existe en el sistema")})
    public ResponseEntity<PaymentMethodResponseDTO> createPaymentMethod(@RequestBody @Valid PaymentMethodRequestDTO paymentMethodRequestDTO) {
        PaymentMethodResponseDTO objPaymentMethodResponse = paymentMethodService.createPaymentMethod(paymentMethodRequestDTO);

        return new ResponseEntity<>(objPaymentMethodResponse, HttpStatus.CREATED);
    }


    @GetMapping("/all")
    @Operation(summary = "Obtener todos los métodos de pago")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa. Devuelve todos los métodos de pago encontrados",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = PaymentMethodResponseDTO.class)))),
            @ApiResponse(responseCode = "404", description = "No se encontraron métodos de pago")})
    public ResponseEntity<List<PaymentMethodResponseDTO>> getAllPaymentMethods() {
            List<PaymentMethodResponseDTO> paymentMethods = paymentMethodService.findAllPaymentMethods();
            return ResponseEntity.ok(paymentMethods);
        }

    @PutMapping("/changestatus/{paymentMethodId}")
    @Operation(summary = "Cambiar el estado de un método de pago",
            description = " cambiar el estado de un método de pago por su ID.",
            parameters = {@Parameter(name = "paymentMethodId", description = "ID de metodo de pago", example = "1")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa. Se cambió el estado del método de pago"),
            @ApiResponse(responseCode = "404", description = "Método de pago no encontrado")
    })
    public ResponseEntity<Void> changePaymentMethodStatusById(@PathVariable("paymentMethodId") Long paymentMethodId) {

            paymentMethodService.changeStatus(paymentMethodId);
            return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping("/allbystatustrue")
    @Operation(summary = "Obtener todos los métodos de pago activos",
            description = "obtener todos los métodos de pago que están activos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa. Devuelve todos los métodos de pago activos",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = PaymentMethodResponseDTO.class)))),
            @ApiResponse(responseCode = "404", description = "No se encontraron métodos de pago activos")
    })
    public ResponseEntity<List<PaymentMethodResponseDTO>> getAllPaymentMethodByStatusTrue() {
        List<PaymentMethodResponseDTO> paymentMethodStatusTrue = paymentMethodService.findAllPaymentMethodByStatusTrue();
        return ResponseEntity.ok(paymentMethodStatusTrue);
    }

    @GetMapping("/{paymentMethodId}")
    @Operation(summary = "Obtener un método de pago por ID",
            description = "obtener un método de pago específico según su ID.",
            parameters = {@Parameter(name = "paymentMethodId", description = "ID de metodo de pago", example = "1")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa. Devuelve el método de pago encontrado",
                    content = @Content(schema = @Schema(implementation = PaymentMethod.class))),
            @ApiResponse(responseCode = "404", description = "Método de pago no encontrado")
    })
    public ResponseEntity<PaymentMethod> getPaymentMethodById(@PathVariable("paymentMethodId") Long paymentMethodId) {
        PaymentMethod paymentMethod = paymentMethodService.findById(paymentMethodId);
        return ResponseEntity.ok(paymentMethod);
    }
}
