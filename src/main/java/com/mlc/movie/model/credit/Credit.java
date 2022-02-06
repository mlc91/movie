package com.mlc.movie.model.credit;

import com.mlc.movie.model.credit.cast.Cast;
import com.mlc.movie.model.credit.crew.Crew;
import com.mlc.movie.model.movie.Movie;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

import static com.mlc.movie.model.credit.cast.Cast.setCastFromCastDTO;
import static com.mlc.movie.model.credit.crew.Crew.setCrewFromCrewDTO;

@Data
@Entity
@Table(name = "CREDIT")
public class Credit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    @OneToMany(mappedBy = "credit", cascade = CascadeType.ALL)
    private List<Cast> casts;

    @OneToMany(mappedBy = "credit", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Crew> crews;

    @OneToOne(mappedBy = "credit")
    private Movie movie;

    public static Credit setCreditFromCreditDTO(CreditDTO creditDTO) {
        Credit credit = new Credit();
        credit.setCasts(creditDTO.getCasts().stream().map(castDTO -> setCastFromCastDTO(castDTO)).collect(Collectors.toList()));
        credit.setCrews(creditDTO.getCrews().stream().map(crewDTO -> setCrewFromCrewDTO(crewDTO)).collect(Collectors.toList()));
        return credit;
    }
}
