package com.mlc.movie.model.movie;

import com.mlc.movie.model.credit.Credit;
import com.mlc.movie.model.genre.Genre;
import com.mlc.movie.model.productionCompany.ProductionCompany;
import com.mlc.movie.model.productionCountry.ProductionCountry;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private String dtoId;
    private boolean adult;
    private String backdropPath;
    private int budget;
    @OneToOne
    private Credit credit;
    @OneToMany
    private List<Genre> genres;
    private String homepage;
    private String originalLanguage;
    private String originalTitle;
    @Lob
    private String overview;
    private Float popularity;
    private String posterPath;
    @OneToMany
    private List<ProductionCompany> productionCompanies;
    @OneToMany
    private List<ProductionCountry> productionCountries;
    private String releaseDate;
    private int revenue;
    private String status;
    private String title;
    private Float voteAverage;
    private int voteCount;

    public static Movie setMovieFromMovieDTO(MovieDTO movieDTO){
        Movie movie = new Movie();
        movie.setDtoId(movieDTO.getId());
        movie.setAdult(movieDTO.isAdult());
        movie.setBackdropPath(movieDTO.getBackdropPath());
        movie.setBudget(movieDTO.getBudget());
        movie.setCredit(movieDTO.getCredit());
        movie.setGenres(movieDTO.getGenres());
        movie.setHomepage(movieDTO.getHomepage());
        movie.setOriginalLanguage(movieDTO.getOriginalLanguage());
        movie.setOriginalTitle(movieDTO.getOriginalTitle());
        movie.setOverview(movieDTO.getOverview());
        movie.setPopularity(movieDTO.getPopularity());
        movie.setPosterPath(movieDTO.getPosterPath());
        movie.setProductionCompanies(movieDTO.getProductionCompanies());
        movie.setProductionCountries(movieDTO.getProductionCountries());
        movie.setReleaseDate(movieDTO.getReleaseDate());
        movie.setRevenue(movieDTO.getRevenue());
        movie.setStatus(movieDTO.getStatus());
        movie.setTitle(movieDTO.getTitle());
        movie.setVoteAverage(movieDTO.getVoteAverage());
        movie.setVoteCount(movieDTO.getVoteCount());
        return movie;
    }
}
