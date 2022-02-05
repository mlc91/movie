package com.mlc.movie.model.credit;

import com.mlc.movie.model.credit.cast.Cast;
import com.mlc.movie.model.credit.crew.Crew;

//@Entity
public class Credit {

   // @Id
   // @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
   // @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    //One to many
    private Cast cast;

    //One to many
    private Crew crew;

    public Credit() {
    }

    public Long getId(){
        return id;
    }

    public Cast getCast() {
        return cast;
    }

    public void setCast(Cast cast) {
        this.cast = cast;
    }

    public Crew getCrew() {
        return crew;
    }

    public void setCrew(Crew crew) {
        this.crew = crew;
    }
}
