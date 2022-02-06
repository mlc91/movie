package com.mlc.movie.model.person;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private String dtoId;
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

    public static Person setPersonFromPersonDTO(PersonDTO personDTO){
        Person person = new Person();
        person.setDtoId(personDTO.getId());
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
