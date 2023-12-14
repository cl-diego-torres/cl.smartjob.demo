package cl.smartjob.demo.controller;

import cl.smartjob.demo.dto.UserDto;
import cl.smartjob.demo.entity.User;
import cl.smartjob.demo.service.UserService;
import io.swagger.annotations.*;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

/**
 * Controlador para gestionar operaciones relacionadas con usuarios.
 */
@RestController
@RequestMapping("/user")
@Api(tags = "User", description = "Operaciones de usuarios")
public class UserController {

    /**
     * Servicio para la gestión de usuarios.
     */
    private final UserService userService;

    /**
     * Constructor para inyectar dependencias.
     *
     * @param userService Servicio de usuarios a inyectar.
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Endpoint para crear un nuevo usuario.
     *
     * @param request DTO que contiene la información del usuario a crear.
     * @return ResponseEntity con el resultado de la operación.
     */
    @PostMapping
    @ApiOperation(value = "Crear Usuario")
    @ApiResponses({
            @ApiResponse(code = 200,  message = "No implementado"),
            @ApiResponse(code = 201,  message = "Usuario creado exitosamente"),
            @ApiResponse(code = 400,  message = "Parametro de usuario no valido")
    })
    private ResponseEntity<?> createUser(
            @RequestBody UserDto request) {
        try {
            return new ResponseEntity<>(userService.createUser(request), HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(handleError(ex), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Maneja las excepciones y devuelve un mapa con un mensaje de error.
     *
     * @param ex Excepción capturada.
     * @return Mapa con el mensaje de error.
     */
    private Map<String,String> handleError(Exception ex) {
        return Collections.singletonMap("mensaje", ex.getMessage());
    }
}
