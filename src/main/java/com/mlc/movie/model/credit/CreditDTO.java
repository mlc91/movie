package com.mlc.movie.model.credit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mlc.movie.model.credit.cast.CastDTO;
import com.mlc.movie.model.credit.crew.CrewDTO;
import lombok.Data;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * The CreditDTO class contains all the properties to retrieve information
 * to the TMDB API to fill in the Credit Entity.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreditDTO {
    @JsonProperty("id")
    private Long tmdbId;
    @JsonProperty("cast")
    private Set<CastDTO> casts;
    @JsonProperty("crew")
    private Set<CrewDTO> crews;

    /**
     * Creates a Credit object from CreditDTO.
     * @param creditDTO
     * @return credit
     */
    public static Credit setCreditFromCreditDTO(CreditDTO creditDTO) {
        Credit credit = new Credit();
        credit.setCast(creditDTO.getCasts().stream().map(CastDTO::setCastFromCastDTO).collect(Collectors.toSet()));
        credit.setCrew(creditDTO.getCrews().stream().map(CrewDTO::setCrewFromCrewDTO).collect(Collectors.toSet()));
        return credit;
    }

}
