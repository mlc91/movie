package com.mlc.movie.model.movie;

import com.mlc.movie.model.movieUser.MovieUser;
import com.mlc.movie.model.credit.Credit;
import com.mlc.movie.model.credit.crew.Crew;
import com.mlc.movie.model.genre.Genre;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "MOVIE")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private Long tmdbId;
    private boolean isAdult;
    private String backdropPath;
    private int budget;
    // TODO PROBLEM WITH SOME EXCEPTION
    @ToString.Exclude
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CREDIT_ID", referencedColumnName = "id", nullable = false)
    private Credit credit;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id")
    private List<Genre> genres;
    private String homepage;
    private String originalLanguage;
    private String originalTitle;
    // TODO OVERVIEW TOO LONG. I HAVE TO USE @LOB ANNOTATION
    @Lob
    private String overview;
    private Float popularity;
    private String posterPath;
    @ToString.Exclude
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private Set<MovieUser> movieUsers;
    private String releaseDate;
    private int revenue;
    private String status;
    private String title;
    private Float voteAverage;
    private int voteCount;

    public Movie() {
    }

    public Map<String, Object> movieDTO(){
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("id", this.getId());
        dto.put("adult", this.isAdult());
        dto.put("backdropPath", this.getBackdropPath());
        dto.put("budget", this.getBudget());
        dto.put("credit", this.getCredit().creditDTO());
        // One movie could have more than one director
        dto.put("director", this.getCredit().getCrew().stream()
                .filter(crew -> crew.getJob().equals("Director"))
                .collect(Collectors.toList())
                .stream().map(Crew::getName));
        dto.put("genres", this.getGenres().stream().map(Genre::genreDTO).collect(Collectors.toList()));
        dto.put("homepage", this.getHomepage());
        dto.put("originalLanguage", this.getOriginalLanguage());
        dto.put("originalTitle", this.getOriginalTitle());
        dto.put("overview", this.getOverview());
        dto.put("popularity", this.getPopularity());
        dto.put("posterPath", this.getPosterPath());
        dto.put("releaseDate", this.getReleaseDate());
        dto.put("revenue", this.getRevenue());
        dto.put("status", this.getStatus());
        dto.put("title", this.getTitle());
        dto.put("voteAverage", this.getVoteAverage());
        dto.put("voteCount", this.getVoteCount());
        return dto;
    }
}
