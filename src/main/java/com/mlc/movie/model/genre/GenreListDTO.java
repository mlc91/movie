package com.mlc.movie.model.genre;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true) //this anotation is in case the api changes
public class GenreListDTO {
    private List<GenreDTO> genres;

}
