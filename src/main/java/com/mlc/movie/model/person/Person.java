package com.mlc.movie.model.person;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long personId;
    private int id;
    private String biography;
    private String birthday;
    private String deathday;
    //One to one
    //private Gender gender;
    private String imdbId;
    private String knownForDepartment;
    private String name;
    private String placeOfBirth;
    private Float popularity;
    private String profilePath;

    //ver cómo distinguir entre cast y crew
    // booleans isCast and isCrew ??

    public Person() {
    }
}