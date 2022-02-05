package com.mlc.movie.model.person;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonAPI {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("biography")
    private String biography;
    @JsonProperty("birthday")
    private String birthday;
    @JsonProperty("deathday")
    private String deathday;
    @JsonProperty("gender")
    private int gender;
    @JsonProperty("imdb_id")
    private String imdbId;
    @JsonProperty("known_for_department")
    private String knownForDepartment;
    @JsonProperty("name")
    private String name;
    @JsonProperty("place_of_birth")
    private String placeOfBirth;
    @JsonProperty("popularity")
    private Float popularity;
    @JsonProperty("profile_path")
    private String profilePath;

    public PersonAPI() {
    }

}
