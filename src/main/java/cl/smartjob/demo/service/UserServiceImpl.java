package cl.smartjob.demo.service;

import cl.smartjob.demo.dto.UserDto;
import cl.smartjob.demo.entity.User;
import cl.smartjob.demo.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

/**
 * Implementación de la interfaz UserService que proporciona servicios relacionados con la gestión de usuarios.
 */
@Service
public class UserServiceImpl implements UserService {

    // Propiedades relacionadas con las expresiones regulares y la clave secreta
    @Value("${user.service.email.regex}")
    private String emailRegex;
    @Value("${user.service.password.regex}")
    private String passwordRegex;

    // Clave secreta para la generación de tokens JWT
    private final SecretKey key;
    // Repositorio para acceder a la base de datos de usuarios
    private final UserRepository userRepository;

    /**
     * Constructor de la clase que se utiliza para inyectar dependencias.
     *
     * @param userRepository Repositorio de usuarios.
     */
    @Autowired
    public UserServiceImpl(UserRepository userRepository, SecretKey key) {

        this.userRepository = userRepository;
        this.key = key;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User createUser(UserDto request) {
        // Validaciones de formato y existencia de usuario
        validateUser(request.getEmail().trim());
        validateEmail(request.getEmail().trim());
        validatePassword(request.getPassword().trim());

        // Creación de un nuevo usuario a partir del DTO
        User user = new User();
        user.setName(request.getName().trim());
        user.setEmail(request.getEmail().trim());
        user.setPassword(request.getPassword().trim());

        // Configuración de fechas y token JWT
        Instant now = Instant.now();
        Date nowDate = Date.from(now);

        user.setCreated(nowDate);
        user.setModified(nowDate);
        user.setLast_login(nowDate);

        user.setToken(generateToken(user.getEmail()));
        user.setIsactive(true);
        user.setPhones(request.getPhones());

        // Guardar el nuevo usuario en la base de datos y devolverlo
        return userRepository.save(user);
    }

    /**
     * Valida el formato del correo electrónico según una expresión regular.
     *
     * @param email Correo electrónico a validar.
     * @throws IllegalArgumentException Si el formato del correo electrónico es inválido.
     */
    private void validateEmail(String email) {
        if (!email.matches(emailRegex)) {
            throw new IllegalArgumentException("Formato de correo inválido");
        }
    }

    /**
     * Valida el formato de la contraseña según una expresión regular.
     *
     * @param password Contraseña a validar.
     * @throws IllegalArgumentException Si el formato de la contraseña es inválido.
     */
    private void validatePassword(String password) {
        if (!password.matches(passwordRegex)) {
            throw new IllegalArgumentException("Formato de contraseña inválido");
        }
    }

    /**
     * Valida si un usuario con el mismo correo electrónico ya está registrado en la base de datos.
     *
     * @param email Correo electrónico a validar.
     * @throws IllegalArgumentException Si el correo electrónico ya está registrado.
     */
    private void validateUser(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("El correo ya registrado");
        }
    }

    /**
     * Genera un token JWT utilizando la clave secreta.
     *
     * @param email Correo electrónico para incluir en el token.
     * @return Token JWT generado.
     */
    private String generateToken(String email) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        return Jwts.builder()
                .id(UUID.randomUUID().toString())
                .issuer(email)
                .issuedAt(now)
                .signWith(key)
                .compact();
    }
}
