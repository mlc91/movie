package com.mlc.movie.model.credit.cast;

import com.mlc.movie.model.credit.Credit;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "CAST")
public class Cast {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private String character;
    private int castOrder;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "credit_id")
    private Credit credit;

    private String name;

    public static Cast setCastFromCastDTO(CastDTO castDTO){
        Cast cast = new Cast();
        cast.setCharacter(castDTO.getCharacter());
        cast.setName(castDTO.getName());
        cast.setCastOrder(castDTO.getOrder());
        return cast;
    }
}
