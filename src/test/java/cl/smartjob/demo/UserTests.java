package cl.smartjob.demo;

import cl.smartjob.demo.entity.User;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Date;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTests {

    @Test
    public void UserTestConstructorAndGetters() {
        User user = new User();

        assertThat(user.getId()).isNull();
        assertThat(user.getName()).isNull();
        assertThat(user.getEmail()).isNull();
        assertThat(user.getPassword()).isNull();
        assertThat(user.getCreated()).isNull();
        assertThat(user.getModified()).isNull();
        assertThat(user.getLast_login()).isNull();
        assertThat(user.getToken()).isNull();
        assertThat(user.isIsactive()).isEqualTo(false);
        assertThat(user.getPhones()).isNull();
    }

    @Test
    public void UserTestSetters() {
        User user = new User();

        user.setId(UUID.randomUUID());
        user.setName("Juan Rodriguez");
        user.setEmail("juan@rodriguez.org");
        user.setPassword("hunter2");
        user.setCreated(new Date());
        user.setModified(new Date());
        user.setLast_login(new Date());
        user.setToken("generatedtoken");
        user.setIsactive(true);
        user.setPhones(Collections.emptyList());

        assertThat(user.getId()).isNotNull();
        assertThat(user.getName()).isEqualTo("Juan Rodriguez");
        assertThat(user.getEmail()).isEqualTo("juan@rodriguez.org");
        assertThat(user.getPassword()).isEqualTo("hunter2");
        assertThat(user.getCreated()).isNotNull();
        assertThat(user.getModified()).isNotNull();
        assertThat(user.getLast_login()).isNotNull();
        assertThat(user.getToken()).isEqualTo("generatedtoken");
        assertThat(user.isIsactive()).isEqualTo(true);
        assertThat(user.getPhones()).isEqualTo(Collections.emptyList());
    }
}
