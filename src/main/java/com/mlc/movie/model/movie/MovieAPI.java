package com.mlc.movie.model.movie;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mlc.movie.model.credit.Credit;
import com.mlc.movie.model.genre.Genre;
import com.mlc.movie.model.productionCompany.ProductionCompany;
import com.mlc.movie.model.productionCompany.ProductionCompanyAPI;
import com.mlc.movie.model.productionCountry.ProductionCountry;
import com.mlc.movie.model.productionCountry.ProductionCountryAPI;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@JsonIgnoreProperties(ignoreUnknown = true) //this anotation is in case the api changes
public class MovieAPI {
    @JsonProperty("id")
    private int id;
    @JsonProperty("adult")
    private boolean adult;
    @JsonProperty("backdrop_path")
    private String backdropPath;
    @JsonProperty("budget")
    private int budget;
    @JsonProperty("genres")
    private Genre[] genres;
    @JsonProperty("original_language")
    private String originalLanguage;
    @JsonProperty("original_title")
    private String originalTitle;
    @JsonProperty("overview")
    private String overview;
    @JsonProperty("popularity")
    private Float popularity;
    @JsonProperty("poster_path")
    private String posterPath;
    @JsonProperty("production_companies")
    private ProductionCompanyAPI[] productionCompanies;
    @JsonProperty("production_countries")
    private ProductionCountryAPI[] productionCountries;
    @JsonProperty("release_date")
    private String releaseDate;
    @JsonProperty("revenue")
    private int revenue;
    @JsonProperty("status")
    private String status;
    @JsonProperty("title")
    private String title;
    @JsonProperty("vote_average")
    private Float voteAverage;
    @JsonProperty("vote_count")
    private int voteCount;

    public MovieAPI() {
    }

}
