package com.mlc.movie.model.credit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mlc.movie.model.credit.cast.CastDTO;
import com.mlc.movie.model.credit.crew.CrewDTO;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

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
        credit.setCast(creditDTO.getCasts().stream().map(CastDTO::setCastFromCastDTO).collect(Collectors.toList()));
        credit.setCrew(creditDTO.getCrews().stream().map(CrewDTO::setCrewFromCrewDTO).collect(Collectors.toList()));
        return credit;
    }

}
