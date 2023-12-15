package com.aceleracion.ecommercegyl.controller;

import com.aceleracion.ecommercegyl.dto.request.RoleRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.RoleResponseDTO;
import com.aceleracion.ecommercegyl.service.service.RoleService;
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
@RequestMapping("/role")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {

        this.roleService = roleService;
    }

    @PostMapping("/create")
    @Operation(summary = "Crear un rol")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Rol creado exitosamente",
                    content = @Content(schema = @Schema(implementation = RoleResponseDTO.class))),
            @ApiResponse(responseCode = "409", description = "El rol ya existe en el sistema")})
    public ResponseEntity<RoleResponseDTO> createRole(@RequestBody @Valid RoleRequestDTO roleRequest) {
        RoleResponseDTO objRoleResponse = roleService.createRole(roleRequest);

        return new ResponseEntity<>(objRoleResponse, HttpStatus.CREATED);
    }


    @Operation(summary = "Busca un rol por ID",
            parameters = {@Parameter(name = "roleId", description = "ID del rol", example = "20")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa, role encontrado por ID",
                    content = @Content(schema = @Schema(implementation = RoleResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Rol no encontrado")})
    @GetMapping("/{roleId}")
    public ResponseEntity<RoleResponseDTO> getRoleById(@PathVariable("roleId") Long roleId) {
        RoleResponseDTO role = roleService.findRoleById(roleId);
        return ResponseEntity.ok(role);

    }

    @GetMapping("/all")
    @Operation(summary = "Obtener todos los roles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa. Devuelve todos los roles encontrados",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = RoleResponseDTO.class)))),
            @ApiResponse(responseCode = "404", description = "No se encontraron roles")})
    public ResponseEntity<List<RoleResponseDTO>> getRoles() {
        List<RoleResponseDTO> roles = roleService.findAllRoles();
        return ResponseEntity.ok(roles);

    }

    @PutMapping("/update/{name}")
    @Operation(summary = "Actualizar un rol",
            description = "Este endpoint se utiliza para actualizar un rol existente por su nombre.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa. Devuelve el rol actualizado",
                    content = @Content(schema = @Schema(implementation = RoleResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida. Compruebe los datos de la solicitud"),
            @ApiResponse(responseCode = "404", description = "Rol no encontrado. No se encontró un rol con el nombre especificado")
    })
    public ResponseEntity<RoleResponseDTO> updateRole(@PathVariable("name") String name,
                                                      @RequestBody @Valid RoleRequestDTO roleRequest) {
        RoleResponseDTO updatedRole = roleService.updateRole(name, roleRequest);

        return ResponseEntity.ok(updatedRole);
    }

}