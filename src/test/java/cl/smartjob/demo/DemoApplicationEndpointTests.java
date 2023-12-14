package cl.smartjob.demo;

import cl.smartjob.demo.dto.UserDto;
import cl.smartjob.demo.entity.Phone;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationEndpointTests {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    @DirtiesContext
    void shouldCreateANewUser() {
        UserDto newUser = new UserDto(
                "Juan Rodriguiez",
                "juan@rodriguez.org",
                "hunter2",
                Collections.singletonList(new Phone(
                        "1234567",
                        "1",
                        "57"
                ))
        );

        ResponseEntity<String> response =
                restTemplate.postForEntity("/user", newUser, String.class);

        assertThat(response.getStatusCode())
                .as("Se esperaba que el usuario sea creado correctamente.")
                .isEqualTo(HttpStatus.CREATED);
    }

    @Test
    @DirtiesContext
    void shouldNotDuplicateAUser() {
        UserDto newUser = new UserDto(
                "Juan Rodriguiez",
                "juan@rodriguez.org",
                "hunter2",
                Collections.singletonList(new Phone(
                        "1234567",
                        "1",
                        "57"
                ))
        );

        ResponseEntity<String> response =
                restTemplate.postForEntity("/user", newUser, String.class);

        assertThat(response.getStatusCode())
                .as("Se esperaba que el usuario sea creado correctamente.")
                .isEqualTo(HttpStatus.CREATED);

        response =
                restTemplate.postForEntity("/user", newUser, String.class);

        assertThat(response.getStatusCode())
                .as("Se esperaba que fallara debido a que el correo ya existe en la base de datos.")
                .isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void shouldNotCreateANewUserWithInvalidEmail() {
        UserDto newUser = new UserDto(
                "Juan Rodriguiez",
                "invalid email",
                "hunter2",
                Collections.singletonList(new Phone(
                        "1234567",
                        "1",
                        "57"
                ))
        );

        ResponseEntity<String> response =
                restTemplate.postForEntity("/user", newUser, String.class);

        assertThat(response.getStatusCode())
                .as("Se esperaba que fallara debido al formato de correo electrónico inválido.")
                .isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void shouldNotCreateANewUserWithInvalidPassword() {
        UserDto newUser = new UserDto(
                "Juan Rodriguiez",
                "juan@rodriguez.org",
                "invalid password",
                Collections.singletonList(new Phone(
                        "1234567",
                        "1",
                        "57"
                ))
        );

        ResponseEntity<String> response =
                restTemplate.postForEntity("/user", newUser, String.class);

        assertThat(response.getStatusCode())
                .as("Se esperaba que fallara debido al formato de contraseña inválido.")
                .isEqualTo(HttpStatus.BAD_REQUEST);
    }
}
