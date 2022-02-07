package com.mlc.movie.model.movie;

import com.mlc.movie.model.credit.Credit;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.mlc.movie.searchHelper.SearchHelper.getCreditFromAPI;

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
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CREDIT_ID", referencedColumnName = "id")
    private Credit credit;
//    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
//    private List<Genre> genres;
    private String homepage;
    private String originalLanguage;
    private String originalTitle;
    @Lob
    private String overview;
    private Float popularity;
    private String posterPath;
//    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
//    private List<ProductionCompany> productionCompanies;
//    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
//    private List<ProductionCountry> productionCountries;
    private String releaseDate;
    private int revenue;
    private String status;
    private String title;
    private Float voteAverage;
    private int voteCount;

    public Map<String, Object> movieDTO(){
        Map<String, Object> dto =new LinkedHashMap<>();
        dto.put("id", this.getId());
        dto.put("homepage", this.getHomepage());
        dto.put("originalTitle", this.getOriginalTitle());
        return dto;
    }

}
