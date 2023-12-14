package cl.smartjob.demo.config;

import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * Configuración de Swagger para documentar la API.
     *
     * @return Docket configurado.
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("cl.smartjob.demo.controller"))
                .build()
                .useDefaultResponseMessages(false);
    }

    /**
     * Crea una instancia de la clave secreta a partir de la propiedad de la aplicación.
     *
     * @param secretKey Valor de la clave secreta definido en las propiedades de la aplicación.
     * @return SecretKey creada.
     */
    @Bean
    public SecretKey secretKey(@Value("${smartjob.secret.key}") String secretKey) {
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }
}
