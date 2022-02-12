package com.mlc.movie.model.credit;

import com.mlc.movie.model.credit.cast.Cast;
import com.mlc.movie.model.credit.crew.Crew;
import com.mlc.movie.model.movie.Movie;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The Credit class contains all the properties to build a credit member of a movie.
 * It contains a set of cast and crew.
 */
@Data
@Entity
public class Credit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "crew_id")
    private Set<Cast> cast;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "crew_id")
    private Set<Crew> crew;
    @ToString.Exclude
    @OneToOne(mappedBy = "credit")
    private Movie movie;

    public Map<String, Object> creditDTO() {
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("id", this.getId());
        dto.put("cast", this.getCast().stream().map(Cast::castDTO).collect(Collectors.toSet()));
        dto.put("crew", this.getCrew().stream().map(Crew::crewDTO).collect(Collectors.toSet()));
        return dto;
    }
}
