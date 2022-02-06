package com.mlc.movie.model.credit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mlc.movie.model.credit.cast.Cast;
import com.mlc.movie.model.credit.cast.CastDTO;
import com.mlc.movie.model.credit.crew.Crew;
import com.mlc.movie.model.credit.crew.CrewDTO;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreditDTO {
    private String id;
    @JsonProperty("cast")
    private List<CastDTO> casts;
    @JsonProperty("crew")
    private List<CrewDTO> crews;

}
