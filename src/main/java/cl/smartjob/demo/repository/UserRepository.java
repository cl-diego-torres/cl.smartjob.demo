package cl.smartjob.demo.repository;

import cl.smartjob.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * Repositorio para realizar operaciones de base de datos relacionadas con la entidad User.
 * Extiende JpaRepository, proporcionando métodos CRUD básicos.
 */
public interface UserRepository extends JpaRepository<User, UUID> {

    /**
     * Busca un usuario por su dirección de correo electrónico.
     *
     * @param email Dirección de correo electrónico del usuario.
     * @return Optional que contiene el usuario si se encuentra, o vacío si no se encuentra.
     */
    Optional<User> findByEmail(String email);
}
