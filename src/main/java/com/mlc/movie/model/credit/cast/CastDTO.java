package com.mlc.movie.model.credit.cast;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CastDTO {
    private Long id;
    private String character;
    private int gender;
    @JsonProperty("known_for_department")
    private String knownForDepartment;
    private String name;
    private int order;

}