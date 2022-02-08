package com.mlc.movie.model.productionCompany;

import com.mlc.movie.model.movie.Movie;
import com.mlc.movie.model.person.Person;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

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
//    @ManyToOne(fetch = FetchType.LAZY)
//    private Movie movie;

    public Map<String, Object> productionCompanyDTO() {
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("id", this.getId());
        dto.put("logoPath", this.getLogoPath());
        dto.put("name", this.getName());
        dto.put("originCountry", this.getOriginCountry());
        return dto;
    }
}
