package com.mlc.movie.model.credit.cast;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CastAPI {
    private Long id;
    private String character;
    private int gender;
    @JsonProperty("known_for_department")
    private String knownForDepartment;
    private String name;
    private int order;

    public CastAPI() {
    }

}
