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
    private Long tmdbId;
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
        dto.put("name", this.getName());
        dto.put("profilePath", this.getProfilePath());
        return dto;
    }

}
