package com.mlc.movie.model.credit;

import com.mlc.movie.model.credit.cast.Cast;
import com.mlc.movie.model.credit.crew.Crew;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Credit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    @OneToMany
    private List<Cast> casts;
    @OneToMany
    private List<Crew> crews;

    public static Credit setCreditFromCreditDTO(CreditDTO creditDTO) {
        Credit credit = new Credit();
        credit.setCasts(credit.getCasts());
        credit.setCrews(credit.getCrews());
        return credit;
    }
}
