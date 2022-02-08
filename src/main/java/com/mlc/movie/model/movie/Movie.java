package com.mlc.movie.model.movie;

import com.mlc.movie.model.credit.Credit;
import com.mlc.movie.model.credit.crew.Crew;
import com.mlc.movie.model.person.Person;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
    @ToString.Exclude
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CREDIT_ID", referencedColumnName = "id", nullable = false)
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
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("id", this.getId());
        dto.put("homepage", this.getHomepage());
        dto.put("originalTitle", this.getOriginalTitle());
        return dto;
    }

}
