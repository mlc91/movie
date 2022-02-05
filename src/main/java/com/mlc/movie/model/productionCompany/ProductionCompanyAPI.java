package com.mlc.movie.model.productionCompany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true) //this anotation is in case the api changes
public class ProductionCompanyAPI {
    @JsonProperty("id")
    private int id;
    @JsonProperty("logo_path")
    private String logoPath;
    @JsonProperty("name")
    private String name;
    @JsonProperty("origin_country")
    private String originCountry;

    public ProductionCompanyAPI() {
    }
}
