package com.mlc.movie.model.credit.crew;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CrewDTO {
    private String id;
    private String department;
    private String job;
    private String name;

}
