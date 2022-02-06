package com.mlc.movie.model.productionCompany;

import com.mlc.movie.model.movie.Movie;
import com.mlc.movie.model.person.Person;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
public class ProductionCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private String logoPath;
    private String name;
    private String originCountry;
    @ManyToOne(fetch = FetchType.EAGER)
    private Movie movie;
}
