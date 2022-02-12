package com.mlc.movie.controller;

import com.mlc.movie.model.userApp.UserApp;
import com.mlc.movie.model.personUser.PersonUser;
import com.mlc.movie.model.person.Person;
import com.mlc.movie.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

import static com.mlc.movie.helper.ProgramHelper.*;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@RequestMapping("/api")
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    UserAppRepository userAppRepository;

    @Autowired
    PersonUserRepository personUserRepository;

    @PostMapping(path = "/person/{tmdbId}")
    public ResponseEntity<Map<String, Object>> addPerson(@PathVariable Long tmdbId, Authentication authentication){
        if (isGuest(authentication)) {
            return new ResponseEntity<>(makeMap("Error", "Unauthorized user"), HttpStatus.UNAUTHORIZED);
        } else {
            if (personRepository.findByTmdbId(tmdbId) == null) {
                Person person = getPersonFromAPI(tmdbId);
                personRepository.save(person);
            }
            UserApp user = userAppRepository.findByNickname(authentication.getName());
            Person personToSave = personRepository.findByTmdbId(tmdbId);
            Set<PersonUser> personIsSaved = personUserRepository.findByUserApp(user).stream()
                    .filter(personUser -> Objects.equals(personUser.getPerson().getId(),
                            personToSave.getId()) && personUser.getUserApp().equals(user))
                    .collect(Collectors.toSet());
            if (personIsSaved.isEmpty()){
                PersonUser personUser = personUserRepository.save(new PersonUser(user, personToSave));
                return new ResponseEntity<>(makeMap("personUserId", personUser.getId()), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(makeMap("Error", "Person already saved"), HttpStatus.FORBIDDEN);
            }
        }
    }

    @GetMapping("/persons")
    private ResponseEntity<Map<String, Object>> getPersons(Authentication authentication){
        if (isGuest(authentication)) {
            return new ResponseEntity<>(makeMap("error", "Unauthorized user"), HttpStatus.UNAUTHORIZED);
        } else {
            Map<String, Object> dto = new LinkedHashMap<>();
            UserApp userApp = userAppRepository.findByNickname(authentication.getName());
            dto.put("persons", userApp.getPersonUsers()
                    .stream().map(personUser -> personUser.getPerson().personDTO()));
            return new ResponseEntity<>(dto, HttpStatus.ACCEPTED);
        }
    }

    @GetMapping(path = "person/delete/{personUserId}")
    public ResponseEntity<Map<String, Object>> deletePerson(@PathVariable Long personUserId, Authentication authentication) {
        if (isGuest(authentication)) {
            return new ResponseEntity<>(makeMap("Error", "Unauthorized user"), HttpStatus.UNAUTHORIZED);
        } else {
            Optional<PersonUser> personUsers = personUserRepository.findById(personUserId);
            if (!personUsers.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            personUserRepository.delete(personUsers.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(path = "admin/persons/{page}")
    public ResponseEntity<Map<String, Object>> getAllPerson(@PathVariable int page, Authentication authentication) {
        if (isGuest(authentication) && authentication.getAuthorities()
                .stream().noneMatch(auth -> auth.getAuthority().equals("ADMIN"))) {
            return new ResponseEntity<>(makeMap("Error", "Unauthorized user"), HttpStatus.UNAUTHORIZED);
        } else {
            Pageable pageable = PageRequest.of(page, 1);
            Page<Person> allPersons = personRepository.findAll(pageable);
            if(allPersons.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                Map<String, Object> dto = new LinkedHashMap<>();
                dto.put("persons", allPersons.stream().map(Person::personDTO));
                return new ResponseEntity<>(dto, HttpStatus.ACCEPTED);
            }
        }
    }
}
