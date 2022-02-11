package com.mlc.movie.model;

import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

// I couldn't use @Data annotation for this class -> it threw an exception
@Entity
public class Fan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private String nickname;
    private String password;
    @ToString.Exclude
    @OneToMany(mappedBy = "fan", cascade = CascadeType.ALL)
    private Set<MovieUser> movieUsers;
    @OneToMany(mappedBy = "fan", cascade = CascadeType.ALL)
    private Set<PersonUser> personUsers;

    public Fan(){
    }

    public Fan(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<MovieUser> getMovieUsers() {
        return movieUsers;
    }

    public void setMovieUsers(Set<MovieUser> movieUsers) {
        this.movieUsers = movieUsers;
    }

    public Set<PersonUser> getPersonUsers() {
        return personUsers;
    }

    public void setPersonUsers(Set<PersonUser> personUsers) {
        this.personUsers = personUsers;
    }
}
