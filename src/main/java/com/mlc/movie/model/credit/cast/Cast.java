package com.mlc.movie.model.credit.cast;

import com.mlc.movie.model.person.Person;

public class Cast {

    //@Id
    //@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    //@GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private Person person;
    private String character;
    private int order;

    public Cast() {
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

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
