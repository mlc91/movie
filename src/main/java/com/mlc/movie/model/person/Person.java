package com.mlc.movie.model.person;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.LinkedHashMap;
import java.util.Map;

@Data
@Entity
@Table(name = "PERSON")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private String tmdbId;
    @Lob
    private String biography;
    private String birthday;
    private String deathday;
    private int gender;
    private String imdbId;
    private String knownForDepartment;
    private String name;
    private String placeOfBirth;
    private Float popularity;
    private String profilePath;
    
    public Map<String, Object> personDTO(){
        Map<String, Object> dto =new LinkedHashMap<>();
        dto.put("id", this.getId());
        dto.put("tmdbId", this.getTmdbId());
        dto.put("biography", this.getBiography());
        dto.put("birthday", this.getBirthday());
        dto.put("deathday", this.getDeathday());
        dto.put("gender", this.getGender());
        dto.put("imdbId", this.getImdbId());
        dto.put("knownForDepartment", this.getKnownForDepartment());
        dto.put("name", this.getName());
        dto.put("placeOfBirth", this.getPlaceOfBirth());
        dto.put("popularity", this.getPopularity());
        dto.put("profilePath", this.getProfilePath());
        return dto;
    }

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
