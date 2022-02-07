package com.mlc.movie.model.productionCountry;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true) //this anotation is in case the api changes
public class ProductionCountryDTO {
    @JsonProperty("iso_3166_1")
    private String iso_3166_1;
    @JsonProperty("name")
    private String name;

}
