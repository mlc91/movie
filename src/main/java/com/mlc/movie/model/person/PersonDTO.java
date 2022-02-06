package com.mlc.movie.model.person;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true) //this anotation is in case the api changes
public class PersonDTO {
    private String id;
    private String biography;
    private String birthday;
    private String deathday;
    private int gender;
    @JsonProperty("imdb_id")
    private String imdbId;
    @JsonProperty("known_for_department")
    private String knownForDepartment;
    private String name;
    @JsonProperty("place_of_birth")
    private String placeOfBirth;
    private Float popularity;
    @JsonProperty("profile_path")
    private String profilePath;

}
