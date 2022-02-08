package com.mlc.movie.model.credit.cast;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.mlc.movie.model.credit.Credit;
import com.mlc.movie.model.person.Person;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
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
    private int castOrder;
    private String character;
    @ManyToOne(fetch = FetchType.LAZY)
    private Credit credit;
    private String name;

    public Map<String, Object> castDTO() {
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("id", this.getId());
        //dto.put("credit", this.getCredit());
        dto.put("character", this.getCharacter());
        dto.put("order", this.getCastOrder());
        return dto;
    }
}
