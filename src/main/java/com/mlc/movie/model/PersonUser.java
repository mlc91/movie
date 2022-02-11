package com.mlc.movie.model;

import com.mlc.movie.model.person.Person;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Data
@Entity
public class PersonUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    @EqualsAndHashCode.Exclude @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fan_id")
    private Fan fan;
    @EqualsAndHashCode.Exclude @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id")
    private Person person;

    public PersonUser() {
    }

    public PersonUser(Fan fan, Person person) {
        this.fan = fan;
        this.person = person;
    }
}
