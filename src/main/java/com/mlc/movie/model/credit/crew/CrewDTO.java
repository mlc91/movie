package com.mlc.movie.model.credit.crew;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CrewDTO {
    private Long id;
    private String department;
    private int gender;
    private String job;
    @JsonProperty("known_for_department")
    private String knownForDepartment;
    private String name;

}
