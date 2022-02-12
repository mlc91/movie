package com.mlc.movie.model.credit.crew;

import com.mlc.movie.model.credit.Credit;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The Crew class contains all the properties to build a crew member of a movie.
 */
@Data
@Entity
@Table(name = "CREW")
public class Crew {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private Long tmdbId;
    private boolean isAdult;
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="credit_id")
    private Credit credit;
    private String department;
    private String gender;
    private String job;
    private String name;
    private String popularity;

    public Crew() {
    }

    public Map<String, Object> crewDTO() {
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("id", this.getId());
        dto.put("adult", this.isAdult());
        dto.put("department", this.getDepartment());
        dto.put("gender", this.getGender());
        dto.put("job", this.getJob());
        dto.put("name", this.getName());
        dto.put("popularity", this.getPopularity());
        return dto;
    }

}
