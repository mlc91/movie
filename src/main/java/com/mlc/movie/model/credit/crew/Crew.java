package com.mlc.movie.model.credit.crew;

import com.mlc.movie.model.person.Person;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Crew {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
//    @OneToMany
//    private List<Person> persons;
    private String job;

    public static Crew setCrewFromCrewDTO(CrewDTO crewDTO){
        Crew crew = new Crew();
        // todo ver cómo guardar persons
        crew.setJob(crewDTO.getJob());
        return crew;
    }

}
