package com.mlc.movie.model.movie;

import com.mlc.movie.model.credit.Credit;
import com.mlc.movie.model.genre.Genre;
import com.mlc.movie.model.productionCompany.ProductionCompany;
import com.mlc.movie.model.productionCountry.ProductionCountry;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

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
    @OneToMany
    private List<Genre> genres;
    @OneToOne
    private Credit credit;
    private String originalLanguage;
    private String originalTitle;
    private String overview;
    private Float popularity;
    private String posterPath;
    @OneToMany
    private List<ProductionCompany> productionCompanies;
    @OneToMany
    private List<ProductionCountry> productionContries;
    private String releaseDate;
    private int revenue;
    private String status;
    private String title;
    private Float voteAverage;
    private int voteCount;

}
