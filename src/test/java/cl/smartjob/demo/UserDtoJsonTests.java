package cl.smartjob.demo;

import cl.smartjob.demo.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class UserDtoJsonTests {

    @Autowired
    private JacksonTester<UserDto> json;

    @Test
    public void UserDtoSerializationTest() throws IOException {
        UserDto userDto = new UserDto(
                "Juan Rodriguez",
                "juan@rodriguez.org",
                "hunter2",
                Collections.emptyList()
        );

        // Asertar la igualdad del JSON completo
        assertThat(json.write(userDto)).isStrictlyEqualToJson("UserDto.json");

        // Asertar la presencia y el valor del campo "name"
        assertThat(json.write(userDto)).hasJsonPathStringValue("@.name");
        assertThat(json.write(userDto)).extractingJsonPathStringValue("@.name").asString()
                .isEqualTo("Juan Rodriguez");

        // Asertar la presencia y el valor del campo "email"
        assertThat(json.write(userDto)).hasJsonPathStringValue("@.email");
        assertThat(json.write(userDto)).extractingJsonPathStringValue("@.email").asString()
                .isEqualTo("juan@rodriguez.org");

        // Asertar la presencia y el valor del campo "password"
        assertThat(json.write(userDto)).hasJsonPathStringValue("@.password");
        assertThat(json.write(userDto)).extractingJsonPathStringValue("@.password").asString()
                .isEqualTo("hunter2");

        // Asertar la presencia de un campo de arreglo "phones"
        assertThat(json.write(userDto)).hasJsonPathArrayValue("@.phones");

        // Opcional: Asertar que la lista de teléfonos está vacía
        assertThat(json.write(userDto)).extractingJsonPathArrayValue("@.phones").isEmpty();
    }
}
