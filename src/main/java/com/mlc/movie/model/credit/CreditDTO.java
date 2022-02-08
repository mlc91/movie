package com.mlc.movie.model.credit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mlc.movie.model.credit.cast.Cast;
import com.mlc.movie.model.credit.cast.CastDTO;
import com.mlc.movie.model.credit.crew.Crew;
import com.mlc.movie.model.credit.crew.CrewDTO;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

import static com.mlc.movie.model.credit.cast.CastDTO.setCastFromCastDTO;
import static com.mlc.movie.model.credit.crew.CrewDTO.setCrewFromCrewDTO;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreditDTO {
    @JsonProperty("id")
    private Long tmdbId;
    @JsonProperty("cast")
    private List<CastDTO> casts;
    @JsonProperty("crew")
    private List<CrewDTO> crews;

    public static Credit setCreditFromCreditDTO(CreditDTO creditDTO) {
        Credit credit = new Credit();
        credit.setCasts(creditDTO.getCasts().stream().map(CastDTO::setCastFromCastDTO).collect(Collectors.toList()));
        credit.setCrews(creditDTO.getCrews().stream().map(CrewDTO::setCrewFromCrewDTO).collect(Collectors.toList()));
        return credit;
    }

}
