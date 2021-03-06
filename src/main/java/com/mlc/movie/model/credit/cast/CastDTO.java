package com.mlc.movie.model.credit.cast;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * The CastDTO class contains all the properties to retrieve information
 * to the TMDB API to fill in the Cast Entity.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CastDTO {
    @JsonProperty("id")
    private Long tmdbId;
    private String character;
    @JsonProperty("adult")
    private boolean isAdult;
    private String gender;
    private String name;
    @JsonProperty("order")
    private int castOrder;
    private String popularity;

    /**
     * Creates a Cast object from CastDTO.
     * @param castDTO
     * @return cast
     */
    public static Cast setCastFromCastDTO(CastDTO castDTO){
        Cast cast = new Cast();
        cast.setTmdbId(castDTO.getTmdbId());
        cast.setAdult(castDTO.isAdult());
        cast.setCharacter(castDTO.getCharacter());
        cast.setGender(castDTO.getGender());
        cast.setName(castDTO.getName());
        cast.setCastOrder(castDTO.getCastOrder());
        cast.setPopularity(castDTO.getPopularity());
        return cast;
    }
}
