package com.mlc.movie.model.genre;

import com.mlc.movie.model.movie.Movie;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The Genre class contains all the properties to be used in Movie Entity
 * to define the movie's genre.
 */
@Data
@Entity
@Table(name = "GENRE")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private Long tmdbId;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie;

    public Map<String, Object> genreDTO() {
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("id", this.getId());
        dto.put("name", this.getName());
        return dto;
    }

}
