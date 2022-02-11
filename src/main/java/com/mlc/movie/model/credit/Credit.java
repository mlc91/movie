package com.mlc.movie.model.credit;

import com.mlc.movie.model.credit.cast.Cast;
import com.mlc.movie.model.credit.crew.Crew;
import com.mlc.movie.model.movie.Movie;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@Entity
public class Credit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "cast_id")
    private List<Cast> cast;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "crew_id")
    private List<Crew> crew;
    @ToString.Exclude
    @OneToOne(mappedBy = "credit")
    private Movie movie;

    public Map<String, Object> creditDTO() {
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("id", this.getId());
        dto.put("cast", this.getCast().stream().map(cast -> cast.castDTO()).collect(Collectors.toList()));
        dto.put("crew", this.getCrew().stream().map(crew -> crew.crewDTO()).collect(Collectors.toList()));
        return dto;
    }
}
