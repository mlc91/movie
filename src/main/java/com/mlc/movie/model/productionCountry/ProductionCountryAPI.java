package com.mlc.movie.model.productionCountry;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@JsonIgnoreProperties(ignoreUnknown = true) //this anotation is in case the api changes
public class ProductionCountryAPI {
    @JsonProperty("iso_3166_1")
    private String iso_3166_1;
    @JsonProperty("name")
    private String name;

    public ProductionCountryAPI() {
    }
}
