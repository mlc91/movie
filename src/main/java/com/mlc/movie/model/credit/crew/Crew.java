package com.mlc.movie.model.credit.crew;

import com.mlc.movie.model.credit.Credit;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "CREW")
public class Crew {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "credit_id")
    private Credit credit;

    private String department;
    private String job;
    private String name;

    public static Crew setCrewFromCrewDTO(CrewDTO crewDTO){
        Crew crew = new Crew();
        crew.setDepartment(crewDTO.getDepartment());
        crew.setJob(crewDTO.getJob());
        crew.setName(crewDTO.getName());
        return crew;
    }

}
