package com.mlc.movie.model.movie;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mlc.movie.model.credit.Credit;
import com.mlc.movie.model.genre.Genre;
import com.mlc.movie.model.genre.GenreDTO;
import com.mlc.movie.model.person.Person;
import com.mlc.movie.model.person.PersonDTO;
import com.mlc.movie.model.productionCompany.ProductionCompany;
import com.mlc.movie.model.productionCompany.ProductionCompanyDTO;
import com.mlc.movie.model.productionCountry.ProductionCountry;
import com.mlc.movie.model.productionCountry.ProductionCountryDTO;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true) //this anotation is in case the api changes
public class MovieDTO {
    @JsonProperty("id")
    private String tmdbId;
    private boolean adult;
    @JsonProperty("backdrop_path")
    private String backdropPath;
    private int budget;
    private List<GenreDTO> genres;
    private String homepage;
    @JsonProperty("original_language")
    private String originalLanguage;
    @JsonProperty("original_title")
    private String originalTitle;
    private String overview;
    private Float popularity;
    @JsonProperty("poster_path")
    private String posterPath;
//    @JsonProperty("production_companies")
//    private List<ProductionCompanyDTO> productionCompanies;
//    @JsonProperty("production_countries")
//    private List<ProductionCountryDTO> productionCountries;
    @JsonProperty("release_date")
    private String releaseDate;
    private int revenue;
    private String status;
    private String title;
    @JsonProperty("vote_average")
    private Float voteAverage;
    @JsonProperty("vote_count")
    private int voteCount;

}
