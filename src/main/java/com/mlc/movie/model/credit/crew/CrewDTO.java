package com.mlc.movie.model.credit.crew;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CrewDTO {
    @JsonProperty("id")
    private Long tmdbId;
    @JsonProperty("adult")
    private boolean isAdult;
    private String department;
    private String gender;
    private String job;
    private String name;
    private String popularity;

    public static Crew setCrewFromCrewDTO(CrewDTO crewDTO){
        Crew crew = new Crew();
        crew.setTmdbId(crewDTO.getTmdbId());
        crew.setAdult(crew.isAdult());
        crew.setDepartment(crewDTO.getDepartment());
        crew.setJob(crewDTO.getJob());
        crew.setName(crewDTO.getName());
        crew.setPopularity(crewDTO.getPopularity());
        return crew;
    }
}
