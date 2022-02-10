package com.mlc.movie.controller;

import com.mlc.movie.model.MovieUser;
import com.mlc.movie.repository.MovieUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static com.mlc.movie.controller.Util.makeMap;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    MovieUserRepository movieUserRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> addUser(@RequestParam String username, @RequestParam String password){

        if (username.isEmpty() || password.isEmpty()){
            return new ResponseEntity<>(makeMap("error", "Missing data"), HttpStatus.FORBIDDEN);
        }
        if(movieUserRepository.findByUsername(username) != null){
            return new ResponseEntity<>(makeMap("error", "Username already in use"), HttpStatus.FORBIDDEN);
        }

        movieUserRepository.save(new MovieUser(username, passwordEncoder.encode(password)));

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
