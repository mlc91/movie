package com.mlc.movie.model.credit.cast;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CastDTO {
    private Long id;
    private String character;
    private String name;
    private int order;

    public static Cast setCastFromCastDTO(CastDTO castDTO){
        Cast cast = new Cast();
        cast.setCharacter(castDTO.getCharacter());
        cast.setName(castDTO.getName());
        cast.setCastOrder(castDTO.getOrder());
        return cast;
    }
}
