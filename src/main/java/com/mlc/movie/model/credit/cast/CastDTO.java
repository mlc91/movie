package com.mlc.movie.model.credit.cast;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CastDTO {
    private String id;
    private String character;
    private String name;
    private int order;

}
