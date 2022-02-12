package com.mlc.movie.model.credit.crew;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * The CrewDTO class contains all the properties to retrieve information
 * to the TMDB API to fill in the Crew Entity.
 */
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

    /**
     * Creates a Crew object from CrewDTO.
     * @param crewDTO
     * @return crew
     */
    public static Crew setCrewFromCrewDTO(CrewDTO crewDTO){
        Crew crew = new Crew();
        crew.setTmdbId(crewDTO.getTmdbId());
        crew.setAdult(crew.isAdult());
        crew.setGender(crewDTO.getGender());
        crew.setDepartment(crewDTO.getDepartment());
        crew.setJob(crewDTO.getJob());
        crew.setName(crewDTO.getName());
        crew.setPopularity(crewDTO.getPopularity());
        return crew;
    }
}
