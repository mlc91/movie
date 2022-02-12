package com.mlc.movie.model.credit.cast;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.mlc.movie.model.credit.Credit;
import com.mlc.movie.model.person.Person;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.LinkedHashMap;
import java.util.Map;

@Data
@Entity
@Table(name = "CAST")
public class Cast {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private Long tmdbId;
    private int castOrder;
    private String character;
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="credit_id")
    private Credit credit;
    private String gender;
    private boolean isAdult;
    private String name;
    private String popularity;

    public Cast() {
    }

    public Map<String, Object> castDTO() {
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("id", this.getId());
        dto.put("adult", this.isAdult());
        dto.put("character", this.getCharacter());
        dto.put("gender", this.getGender());
        dto.put("order", this.getCastOrder());
        dto.put("name", this.getName());
        dto.put("popularity", this.getPopularity());
        return dto;
    }
}
