package com.mlc.movie.model.movieUser;

import com.mlc.movie.model.userApp.UserApp;
import com.mlc.movie.model.movie.Movie;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
public class MovieUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    @EqualsAndHashCode.Exclude @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userApp_id")
    private UserApp userApp;
    @EqualsAndHashCode.Exclude @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    // I had to add this constructor cause of this ex: "No default constructor for entity:  : com.mlc.movie.model.movieUser.MovieUser"
    public MovieUser(){

    }

    public MovieUser(UserApp userApp, Movie movie) {
        this.userApp = userApp;
        this.movie = movie;
    }
}
