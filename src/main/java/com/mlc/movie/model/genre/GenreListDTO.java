package com.mlc.movie.model.genre;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

import static com.mlc.movie.model.genre.GenreDTO.setGenreFromGenreDTO;

@Data
@JsonIgnoreProperties(ignoreUnknown = true) //this anotation is in case the api changes
public class GenreListDTO {
    private List<GenreDTO> genres;

    public static List<Genre> setGenresFromGenreListDTO(GenreListDTO genreListDTO){
        List<Genre> genres = genreListDTO.getGenres().
                stream().
                map(genreDTO -> setGenreFromGenreDTO(genreDTO)).collect(Collectors.toList());
        return genres;
    }
}
