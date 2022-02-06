package com.mlc.movie.model.genre;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "GENRE")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private int tmdbId;
    private String name;

    public static Genre setGenreFromGenreDTO(GenreDTO genreDTO){
        Genre genre = new Genre();
        genre.setTmdbId(genreDTO.getTmdbId());
        genre.setName(genreDTO.getName());
        return genre;
    }

    public static List<Genre> setGenresFromGenreListDTO(GenreListDTO genreListDTO){
        List<Genre> genres = genreListDTO.getGenres().
                stream().
                map(genreDTO -> setGenreFromGenreDTO(genreDTO)).collect(Collectors.toList());
        return genres;
    }
}
