package com.mlc.movie.model.person;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true) //this anotation is in case the api changes
public class PersonDTO {
    @JsonProperty("id")
    private Long tmdbId;
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

    public static Person setPersonFromPersonDTO(PersonDTO personDTO){
        Person person = new Person();
        person.setTmdbId(personDTO.getTmdbId());
        person.setBiography(personDTO.getBiography());
        person.setBirthday(personDTO.getBirthday());
        person.setDeathday(personDTO.getDeathday());
        person.setGender(personDTO.getGender());
        person.setImdbId(personDTO.getImdbId());
        person.setKnownForDepartment(personDTO.getKnownForDepartment());
        person.setName(personDTO.getName());
        person.setPlaceOfBirth(personDTO.getPlaceOfBirth());
        person.setPopularity(personDTO.getPopularity());
        person.setProfilePath(personDTO.getProfilePath());
        return person;
    }

}
