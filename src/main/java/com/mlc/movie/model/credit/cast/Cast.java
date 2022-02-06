package com.mlc.movie.model.credit.cast;

import com.mlc.movie.model.person.Person;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Cast {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long castId;
    @OneToMany
    private List<Person> persons;
    private String character;
    private int castOrder;
}
