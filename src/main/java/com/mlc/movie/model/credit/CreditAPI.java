package com.mlc.movie.model.credit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mlc.movie.model.credit.cast.CastAPI;
import com.mlc.movie.model.credit.crew.CrewAPI;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreditAPI {

    private Long id;
    private CastAPI[] cast;
    private CrewAPI[] crew;

    public CreditAPI() {
    }

}
