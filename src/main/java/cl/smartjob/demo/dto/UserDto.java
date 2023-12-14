package cl.smartjob.demo.dto;

import cl.smartjob.demo.entity.Phone;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * DTO (Data Transfer Object) que representa la información del usuario.
 * Se utiliza para transferir datos entre capas de la aplicación.
 */
public class UserDto {

    @Schema(description = "Nombre del usuario", example = "Juan Rodriguez")
    private String name;
    @Schema(description = "Correo electrónico del usuario", example = "juan@rodriguez.org")
    private String email;
    @Schema(description = "Contraseña del usuario", example = "hunter2")
    private String password;
    @Schema(description = "Lista de teléfonos del usuario")
    private List<Phone> phones;

    /**
     * Constructor por defecto necesario para frameworks como Jackson.
     */
    public UserDto() {
    }

    /**
     * Constructor que inicializa los atributos de la clase.
     *
     * @param name    Nombre del usuario.
     * @param email   Correo electrónico del usuario.
     * @param password Contraseña del usuario.
     * @param phones  Lista de teléfonos del usuario.
     */
    public UserDto(String name, String email, String password, List<Phone> phones) {
        setName(name);
        setEmail(email);
        setPassword(password);
        setPhones(phones);
    }

    /**
     *   Getters y Setters de los atributos de la clase.
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }
}
