package com.mlc.movie.model.credit.crew;

import com.mlc.movie.model.person.Person;

//@Entity
public class Crew {

    //@Id
    //@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    //@GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private Person person;
    private String job;

    public Crew() {
    }

    public Long getId(){
        return id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
