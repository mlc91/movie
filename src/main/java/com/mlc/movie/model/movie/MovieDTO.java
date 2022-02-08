package com.mlc.movie.model.movie;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mlc.movie.model.credit.Credit;
import com.mlc.movie.model.credit.CreditDTO;
import com.mlc.movie.model.credit.cast.CastDTO;
import com.mlc.movie.model.genre.GenreDTO;
import com.mlc.movie.model.productionCompany.ProductionCompanyDTO;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

import static com.mlc.movie.searchHelper.SearchHelper.getCreditFromAPI;

@Data
@JsonIgnoreProperties(ignoreUnknown = true) //this anotation is in case the api changes
public class MovieDTO {
    @JsonProperty("id")
    private Long tmdbId;
    private boolean isAdult;
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
    // Not retrieve from the "movie JSON"
    private CreditDTO creditDTO;

    public static Movie setMovieFromMovieDTO(MovieDTO movieDTO){
        Movie movie = new Movie();
        movie.setTmdbId(movieDTO.getTmdbId());
        movie.setAdult(movieDTO.isAdult());
        movie.setBackdropPath(movieDTO.getBackdropPath());
        movie.setBudget(movieDTO.getBudget());
        movie.setCredit(CreditDTO.setCreditFromCreditDTO(movieDTO.getCreditDTO()));
        movie.setGenres(movieDTO.getGenres().stream().map(GenreDTO::setGenreFromGenreDTO).collect(Collectors.toList()));
        movie.setHomepage(movieDTO.getHomepage());
        movie.setOriginalLanguage(movieDTO.getOriginalLanguage());
        movie.setOriginalTitle(movieDTO.getOriginalTitle());
        movie.setOverview(movieDTO.getOverview());
        movie.setPopularity(movieDTO.getPopularity());
        movie.setPosterPath(movieDTO.getPosterPath());
//        movie.setProductionCompanies(movieDTO.getProductionCompanies()
//                .stream().map(ProductionCompanyDTO::setPCompanyFromPCompanyDTO).collect(Collectors.toList()));
//        movie.setProductionCountries(movieDTO.getProductionCountries());
        movie.setReleaseDate(movieDTO.getReleaseDate());
        movie.setRevenue(movieDTO.getRevenue());
        movie.setStatus(movieDTO.getStatus());
        movie.setTitle(movieDTO.getTitle());
        movie.setVoteAverage(movieDTO.getVoteAverage());
        movie.setVoteCount(movieDTO.getVoteCount());
        return movie;
    }

}
