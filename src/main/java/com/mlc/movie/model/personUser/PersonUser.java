package com.mlc.movie.model.personUser;

import com.mlc.movie.model.userApp.UserApp;
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
    @JoinColumn(name = "userApp_id")
    private UserApp userApp;
    @EqualsAndHashCode.Exclude @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id")
    private Person person;

    public PersonUser() {
    }

    public PersonUser(UserApp userApp, Person person) {
        this.userApp = userApp;
        this.person = person;
    }
}
