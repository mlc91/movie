package com.mlc.movie.model.movie;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mlc.movie.model.productionCompany.ProductionCompanyAPI;
import com.mlc.movie.model.productionCountry.ProductionCountryAPI;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true) //this anotation is in case the api changes
public class MovieDTO {
    private int id;
    private boolean adult;
    @JsonProperty("backdrop_path")
    private String backdropPath;
    private int budget;
    private int genreId;
    @JsonProperty("original_language")
    private String originalLanguage;
    @JsonProperty("original_title")
    private String originalTitle;
    private String overview;
    private Float popularity;
    @JsonProperty("poster_path")
    private String posterPath;
    @JsonProperty("production_companies")
    private ProductionCompanyAPI[] productionCompanies;
    @JsonProperty("production_countries")
    private ProductionCountryAPI[] productionCountries;
    @JsonProperty("release_date")
    private String releaseDate;
    private int revenue;
    private String status;
    private String title;
    @JsonProperty("vote_average")
    private Float voteAverage;
    @JsonProperty("vote_count")
    private int voteCount;

    public MovieDTO() {
    }

}
