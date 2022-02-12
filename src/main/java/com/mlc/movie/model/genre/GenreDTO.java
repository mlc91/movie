package com.mlc.movie.model.genre;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * The GenreDTO class contains all the properties to retrieve information
 * to the TMDB API to fill in the Genre Entity.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true) //this anotation is in case the api changes
public class GenreDTO {
    @JsonProperty("id")
    private Long tmdbId;
    private String name;

    /**
     * Creates a Genre object from GenreDTO.
     * @param genreDTO
     * @return cast
     */
    public static Genre setGenreFromGenreDTO(GenreDTO genreDTO){
        Genre genre = new Genre();
        genre.setTmdbId(genreDTO.getTmdbId());
        genre.setName(genreDTO.getName());
        return genre;
    }

}
