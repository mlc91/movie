package com.mlc.movie.model.credit.crew;

import com.mlc.movie.model.credit.Credit;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.LinkedHashMap;
import java.util.Map;

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

    public Map<String, Object> crewDTO() {
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("id", this.getId());
        dto.put("department", this.getDepartment());
        dto.put("job", this.getJob());
        dto.put("name", this.getName());
        return dto;
    }

}
