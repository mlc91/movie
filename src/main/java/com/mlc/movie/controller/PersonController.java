package com.mlc.movie.controller;

import com.mlc.movie.model.person.Person;
import com.mlc.movie.model.person.PersonDTO;
import org.springframework.web.bind.annotation.*;
import com.mlc.movie.repository.PersonRepository;
import com.mlc.movie.searchHelper.SearchHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static com.mlc.movie.controller.Util.makeMap;
import static com.mlc.movie.model.person.Person.setPersonFromPersonDTO;

@RestController
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    @RequestMapping(path = "/person/{personId}", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> addPerson(@PathVariable String personId){
        PersonDTO personDTO = SearchHelper.getPersonFromAPI(personId);
        Person person = setPersonFromPersonDTO(personDTO);

        if(personRepository.findByDtoId(personId) != null){
            return new ResponseEntity<>(makeMap("Error", "Person already saved!"), HttpStatus.FORBIDDEN);
        }

        personRepository.save(person);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
