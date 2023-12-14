package cl.smartjob.demo.service;

import cl.smartjob.demo.dto.UserDto;
import cl.smartjob.demo.entity.User;

/**
 * Interfaz que define los servicios relacionados con la gestión de usuarios.
 */
public interface UserService {

    /**
     * Crea un nuevo usuario a partir de un objeto UserDto.
     *
     * @param userDto Objeto UserDto que contiene la información del usuario a crear.
     * @return El usuario creado.
     */
    User createUser(UserDto userDto);
}
