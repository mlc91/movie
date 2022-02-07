package com.mlc.movie.model.genre;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mlc.movie.model.movie.Movie;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@JsonIgnoreProperties(ignoreUnknown = true) //this anotation is in case the api changes
public class GenreDTO {
    @JsonProperty("id")
    private Long tmdbId;
    private String name;

    public static Genre setGenreFromGenreDTO(GenreDTO genreDTO){
        Genre genre = new Genre();
        genre.setTmdbId(genreDTO.getTmdbId());
        genre.setName(genreDTO.getName());
        return genre;
    }

}
