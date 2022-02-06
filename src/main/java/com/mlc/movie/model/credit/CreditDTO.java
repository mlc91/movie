package com.mlc.movie.model.credit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mlc.movie.model.credit.cast.CastDTO;
import com.mlc.movie.model.credit.crew.CrewDTO;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreditDTO {

    private Long id;
    private List<CastDTO> cast;
    private List<CrewDTO> crew;
}
