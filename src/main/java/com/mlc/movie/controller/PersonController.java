package com.mlc.movie.controller;

import com.mlc.movie.model.userApp.UserApp;
import com.mlc.movie.model.personUser.PersonUser;
import com.mlc.movie.model.person.Person;
import com.mlc.movie.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

import static com.mlc.movie.helper.ProgramHelper.*;

/**
 * The PersonController class implements the Person controller logic.
 * The user will use these methods to save, delete and list all their
 * favorite members of a crew or cast (such as actors, directors, writers, etc.).
 */
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

    /**
     * Save a PersonUser in the DB. If the Person doesn't exist in the DB, it save it too.
     * @param tmdbId
     * @param authentication
     * @return responseEntity
     */
    @PostMapping(path = "/person/{tmdbId}")
    public ResponseEntity<Map<String, Object>> addPerson(@PathVariable Long tmdbId, Authentication authentication){
        if (isGuest(authentication)) {
            return new ResponseEntity<>(makeMap("Error", "Unauthorized user"), HttpStatus.UNAUTHORIZED);
        } else {
            // If the Person isn't saved, retrieve information from the TMDB API
            if (personRepository.findByTmdbId(tmdbId) == null) {
                Person person = getPersonFromAPI(tmdbId);
                personRepository.save(person);
            }
            // Get the current User and the Person to save to create a new PersonUser
            UserApp user = userAppRepository.findByNickname(authentication.getName());
            Person personToSave = personRepository.findByTmdbId(tmdbId);
            // Checks if the Person is already saved by the current User. If not, it will be saved
            Set<PersonUser> personIsSaved = personUserRepository.findByUserApp(user).stream()
                    .filter(personUser -> Objects.equals(personUser.getPerson().getId(),
                            personToSave.getId()) && personUser.getUserApp().equals(user)).collect(Collectors.toSet());
            if (personIsSaved.isEmpty()){
                PersonUser personUser = personUserRepository.save(new PersonUser(user, personToSave));
                return new ResponseEntity<>(makeMap("personUserId", personUser.getId()), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(makeMap("Error", "Person already saved"), HttpStatus.FORBIDDEN);
            }
        }
    }

    /**
     * Retrieve all the Person from the DB that belongs to the current User.
     * @param authentication
     * @return responseEntity
     */
    @GetMapping("/persons")
    private ResponseEntity<Map<String, Object>> getPersons(Authentication authentication){
        if (isGuest(authentication)) {
            return new ResponseEntity<>(makeMap("error", "Unauthorized user"), HttpStatus.UNAUTHORIZED);
        } else {
            Map<String, Object> dto = new LinkedHashMap<>();
            UserApp userApp = userAppRepository.findByNickname(authentication.getName());
            dto.put("persons", userApp.getPersonUsers().stream().map(personUser -> personUser.getPerson().personDTO()));
            return new ResponseEntity<>(dto, HttpStatus.ACCEPTED);
        }
    }

    /**
     * Delete a PersonUser from the DB.
     * @param @PathVariable personUserId
     * @param authentication
     * @return responseEntity
     */
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

}
