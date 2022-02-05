package com.mlc.movie.model.movie;

import com.mlc.movie.model.credit.Credit;
import com.mlc.movie.model.genre.Genre;
import com.mlc.movie.model.productionCompany.ProductionCompany;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long movieId;
    private int id;
    private boolean adult;
    private String backdropPath;
    private int budget;
    //One to many
    //private Genre[] genres;
    //One to one
    //private Credit credit;
    private String originalLanguage;
    private String originalTitle;
    private String overview;
    private Float popularity;
    private String posterPath;
    // One to many
    // private ProductionCompany[] productionCompanies;
    // One to many
    // private ProductionCountry[] productionContries;
    private String releaseDate;
    private int revenue;
    private String status;
    private String title;
    private Float voteAverage;
    private int voteCount;

    public Movie() {
    }

}
