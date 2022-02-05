package com.mlc.movie.model.credit;

import com.mlc.movie.model.credit.cast.Cast;
import com.mlc.movie.model.credit.crew.Crew;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
public class Credit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long creditId;
    @OneToMany
    private List<Cast> casts;
    @OneToMany
    private List<Crew> crews;

    public Credit() {
    }
}
