package com.mlc.movie.model.credit.crew;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import static com.mlc.movie.model.person.PersonDTO.setPersonFromPersonDTO;
import static com.mlc.movie.searchHelper.SearchHelper.getPersonFromAPI;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CrewDTO {
    @JsonProperty("id")
    private Long tmdbId;
    private String department;
    private String job;
    private String name;

    public static Crew setCrewFromCrewDTO(CrewDTO crewDTO){
        Crew crew = new Crew();
        crew.setDepartment(crewDTO.getDepartment());
        crew.setJob(crewDTO.getJob());
        crew.setName(crewDTO.getName());
        return crew;
    }

}
