package com.aceleracion.ecommercegyl.controller;



import com.aceleracion.ecommercegyl.dto.request.UserFindRequestDTO;
import com.aceleracion.ecommercegyl.dto.request.UserRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.UserResponseDto;
import com.aceleracion.ecommercegyl.service.service.UserService;
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
@RequestMapping("/user")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;

    }

    @PostMapping("/create")
    @Operation(summary = "Crea un user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario creado exitosamente",
                    content = @Content(schema = @Schema(implementation = UserResponseDto.class))),
            @ApiResponse(responseCode = "409", description = "El usuario ya existe en el sistema")})
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserRequestDTO userRequestDto) {
        return new ResponseEntity<>(userService.createUser(userRequestDto), HttpStatus.CREATED);
    }

    @GetMapping("/{userDni}")
    @Operation(summary = "Obtiene un cliente por su DNI",
            parameters = {@Parameter(name = "userDni", description = "dni del usuario", example = "43282873")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa, usuario obtenido por DNI",
                    content = @Content(schema = @Schema(implementation = UserResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")})
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable("userDni") String userDni) {

        return new ResponseEntity<>(userService.findUserByDni(userDni), HttpStatus.OK);
    }

    @GetMapping("/all")
    @Operation(summary = "Obtener todos los usuarios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Operación exitosa. Usuarios encontrados",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserResponseDto.class)))),
            @ApiResponse(responseCode = "404", description = "No se encontró ningún usuario")})
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/name")
    @Operation(summary = "Obtener un usuario por nombre",
            parameters = {@Parameter(name = "name", description = "Nombre del cliente", example = "Martin")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa, usuario obtenido por nombre",
                    content = @Content(schema = @Schema(implementation = UserResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")})
    public ResponseEntity<UserResponseDto> getUserByName(@Valid @RequestBody UserFindRequestDTO userFindRequestDTO) {

        return new ResponseEntity<>(userService.findUserByName(userFindRequestDTO), HttpStatus.OK);
    }

    @PutMapping("/changestatus/{dni}")
    @Operation(summary = "Cambiar el estado de un usuario por su DNI",
            parameters = {@Parameter(name = "dni", description = "DNI del usuario", example = "22598426")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estado del usuario cambiado exitosamente",
                    content = @Content(schema = @Schema(implementation = UserResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")})
    public ResponseEntity<String> changeStatus(@PathVariable("dni") String dni) {

        userService.changeStatus(dni);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update")
    @Operation(summary = "Actualizar un usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario actualizado exitosamente",
                    content = @Content(schema = @Schema(implementation = UserResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")})
    public ResponseEntity<UserResponseDto> updateUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDto updateUser = userService.updateUser(userRequestDTO);
        return ResponseEntity.ok(updateUser);
    }


    @PutMapping("/ceo/{dni}")
    @Operation(summary = "Cambiar el rol de un usuario por Director Ejecutivo",
            parameters = {@Parameter(name = "dni", description = "DNI del usuario", example = "22598426")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estado del usuario cambiado exitosamente",
                    content = @Content(schema = @Schema(implementation = UserResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")})
    public ResponseEntity<String> changeUserRoleByCeo(@PathVariable("dni") String dni) {
        userService.changeRolebyCeo(dni);
        return ResponseEntity.ok("El rol del usuario se ha cambiado exitosamente.");
    }

    @GetMapping("/active")
    @Operation(summary = "Busca todos los usuarios activos ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de Usuarios activos se encontro exitosamente",
                    content = @Content(schema = @Schema(implementation = UserResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Usuarios no encontrado")})
    public ResponseEntity<List<UserResponseDto>> findAllByStatus() {
        List<UserResponseDto> userResponseList = userService.findAllByStatus();
        return ResponseEntity.ok(userResponseList);
    }

    @PutMapping("/manager/{dni}")
    @Operation(summary = "Cambiar el rol de un usuario por Manager",
            parameters = {@Parameter(name = "dni", description = "DNI del usuario", example = "22598426")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estado del usuario cambiado exitosamente",
                    content = @Content(schema = @Schema(implementation = UserResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")})
    public ResponseEntity<String> changeUserRoleByManager(@PathVariable("dni") String dni) {
        userService.changeRoleByManager(dni);
        return ResponseEntity.ok("El rol del usuario se ha cambiado exitosamente.");
    }

    @GetMapping("/active/{branchId}")
    @Operation(summary = "Busca todas las sucursales activas",
            parameters = {@Parameter(name = "branchId", description = "Id de la sucursale", example = "5")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de sucursales activas se encontraron exitosamente",
                    content = @Content(schema = @Schema(implementation = UserResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Sucursales no encontrado")})
    public ResponseEntity<List<UserResponseDto>> getAllActiveUsersByBranch(@PathVariable("branchId") Long branchId) {
        List<UserResponseDto> userResponseList = userService.findAllByBranchAndStatusTrue(branchId);
        return ResponseEntity.ok(userResponseList);
    }

    @GetMapping("/all/{branchId}")
    @Operation(summary = "Busca todos los clientes según la sucursal",
        parameters = {@Parameter(name = "branchId", description = "Id de la sucursal", example = "5")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de clientes según la sucursal encontrados exitosamente",
                    content = @Content(schema = @Schema(implementation = UserResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Clientes no encontrados")})
    public ResponseEntity<List<UserResponseDto>> getAllUsersByBranch(@PathVariable("branchId") Long branchId) {
        List<UserResponseDto> userResponseList = userService.findAllByBranch(branchId);
        return ResponseEntity.ok(userResponseList);
    }
}
