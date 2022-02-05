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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CastAPI[] getCast() {
        return cast;
    }

    public void setCast(CastAPI[] cast) {
        this.cast = cast;
    }

    public CrewAPI[] getCrew() {
        return crew;
    }

    public void setCrew(CrewAPI[] crew) {
        this.crew = crew;
    }
}
