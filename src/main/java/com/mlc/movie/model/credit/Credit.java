package com.mlc.movie.model.credit;

import com.mlc.movie.model.credit.cast.Cast;
import com.mlc.movie.model.credit.crew.Crew;
import com.mlc.movie.model.movie.Movie;
import lombok.Data;
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
    @OneToMany(mappedBy = "credit", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Crew> crews;
    @OneToOne(mappedBy = "credit")
    private Movie movie;

    public Map<String, Object> creditDTO() {
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("id", this.getId());
        dto.put("casts", this.getCast().stream().map(cast -> cast.castDTO()).collect(Collectors.toList()));
        dto.put("crews", this.getCrews().stream().map(crew -> crew.crewDTO()).collect(Collectors.toList()));
        return dto;
    }

}
