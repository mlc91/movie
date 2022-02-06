package com.mlc.movie.model.productionCountry;

import com.mlc.movie.model.movie.Movie;
import com.mlc.movie.model.person.Person;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
public class ProductionCountry {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private String iso_3166_1;
    private String name;
    @ManyToOne(fetch = FetchType.EAGER)
    private Movie movie;

}
