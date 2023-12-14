package cl.smartjob.demo.entity;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.*;

/**
 * Entidad que representa la información de un teléfono.
 * Se utiliza para mapear la tabla 'phones' en la base de datos.
 */
@Entity
@Table(name = "phones")
public class Phone {

    @Schema(description = "Numero de telefono", example = "1234567")
    @Id
    private String number;
    @Schema(description = "codigo de ciudad", example = "1")
    private String citycode;
    @Schema(description = "codigo de pais", example = "57", type = "String")
    private String countrycode;

    /**
     * Constructor por defecto necesario para JPA.
     */
    public Phone() {
    }

    /**
     * Constructor que inicializa los atributos de la clase.
     *
     * @param number     Número de teléfono.
     * @param citycode   Código de ciudad.
     * @param countrycode Código de país.
     */
    public Phone(String number, String citycode, String countrycode) {
        setNumber(number);
        setCitycode(citycode);
        setCountrycode(countrycode);
    }

    /**
     *   Getters y Setters de los atributos de la clase.
     */
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    public String getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }
}
