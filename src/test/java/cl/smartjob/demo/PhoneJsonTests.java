package cl.smartjob.demo;

import cl.smartjob.demo.entity.Phone;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class PhoneJsonTests {

    @Autowired
    private JacksonTester<Phone> json;

    @Test
    public void PhoneSerializationTest() throws IOException {
        Phone phone = new Phone(
                "1234567",
                "1",
                "57"
        );

        // Asertar la igualdad del JSON completo
        assertThat(json.write(phone)).isStrictlyEqualToJson("Phone.json");

        // Asertar la presencia y el valor del campo "number"
        assertThat(json.write(phone)).hasJsonPathStringValue("@.number");
        assertThat(json.write(phone)).extractingJsonPathStringValue("@.number").asString()
                .isEqualTo("1234567");

        // Asertar la presencia y el valor del campo "citycode"
        assertThat(json.write(phone)).hasJsonPathStringValue("@.citycode");
        assertThat(json.write(phone)).extractingJsonPathStringValue("@.citycode").asString()
                .isEqualTo("1");

        // Asertar la presencia y el valor del campo "countrycode"
        assertThat(json.write(phone)).hasJsonPathStringValue("@.countrycode");
        assertThat(json.write(phone)).extractingJsonPathStringValue("@.countrycode").asString()
                .isEqualTo("57");
    }
}
