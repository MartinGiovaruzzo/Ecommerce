package com.aceleracion.ecommercegyl.controller;

import com.aceleracion.ecommercegyl.dto.request.CustomerRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.CustomerResponseDTO;
import com.aceleracion.ecommercegyl.service.service.CustomerService;
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

@CrossOrigin(origins = "http://gyl-ecommerce.s3-website-us-east-1.amazonaws.com/")
@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @PostMapping("/create")
    @Operation(summary = "Crea un cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente creado exitosamente",
                    content = @Content(schema = @Schema(implementation = CustomerResponseDTO.class))),
            @ApiResponse(responseCode = "409", description = "El cliente ya existe en el sistema")})
    public ResponseEntity<CustomerResponseDTO> createCustomer(@RequestBody @Valid CustomerRequestDTO customerRequestDTO) {
        CustomerResponseDTO objCustomerResponse = customerService.createCustomer(customerRequestDTO);

        return new ResponseEntity<>(objCustomerResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{customerDni}")
    @Operation(summary = "Obtiene un cliente por su DNI",
            parameters = {@Parameter(name = "customerDni", description = "dni del cliente", example = "120")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa, cliente obtenido por DNI",
                    content = @Content(schema = @Schema(implementation = CustomerResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")})
    public ResponseEntity<CustomerResponseDTO> getCustomerByDni(@PathVariable("customerDni") @NotNull String customerDni) {
        CustomerResponseDTO customer = customerService.findCustomerByDni(customerDni);

        return ResponseEntity.ok(customer);

    }

    @GetMapping("/all")
    @Operation(summary = "Obtener todos los clientes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Operación exitosa. Clientes encontrados",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = CustomerResponseDTO.class)))),
            @ApiResponse(responseCode = "404", description = "No se encontró ningún cliente")})
    public ResponseEntity<List<CustomerResponseDTO>> getAllCustomers() {
        List<CustomerResponseDTO> customers = customerService.findAllCustomers();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/allstatustrue")
    @Operation(summary = "Obtener todos los clientes activos ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Clientes encontrados",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = CustomerResponseDTO.class))))
    })
    public ResponseEntity<List<CustomerResponseDTO>> getAllByStatus() {
        List<CustomerResponseDTO> customers = customerService.findAllByStatus();
        return ResponseEntity.ok(customers);
    }


    @GetMapping("/nameandlastname")
    @Operation(summary = "Obtener un cliente por nombre y apellido",
            parameters = {@Parameter(name = "name", description = "Nombre del cliente", example = "Valentina"),
                    @Parameter(name = "lastname", description = "Apellido del cliente", example = "Quiroga")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa, cliente obtenido por nombre y apellido",
                    content = @Content(schema = @Schema(implementation = CustomerResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")})
    public ResponseEntity<CustomerResponseDTO> getCustomerByNameAndLastName(@RequestParam("name") String name, @RequestParam("lastname") String lastname) {
        CustomerResponseDTO customer = customerService.findCustomerByName(name, lastname);
        return ResponseEntity.ok(customer);
    }

    @PutMapping("/{dni}")
    @Operation(summary = "Actualizar un cliente por su DNI",
            parameters = {@Parameter(name = "dni", description = "DNI del cliente", example = "34598426")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente actualizado exitosamente",
                    content = @Content(schema = @Schema(implementation = CustomerResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")})
    public ResponseEntity<CustomerResponseDTO> updateCustomer(@PathVariable("dni") String dni,
                                                              @RequestBody @Valid CustomerRequestDTO customerRequestDTO) {
        CustomerResponseDTO updatedCustomer = customerService.updateCustomer(dni, customerRequestDTO);

        return ResponseEntity.ok(updatedCustomer);
    }

    @PutMapping("/changestatus/{dni}")
    @Operation(summary = "Cambiar el estado de un cliente por su DNI",
            parameters = {@Parameter(name = "dni", description = "DNI del cliente", example = "34598426")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estado del cliente cambiado exitosamente",
                    content = @Content(schema = @Schema(implementation = CustomerResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")})
    public ResponseEntity<Void> changeCustomerStatusByDni(@PathVariable("dni") String dni) {
        customerService.changeCustomerStatusByDni(dni);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
