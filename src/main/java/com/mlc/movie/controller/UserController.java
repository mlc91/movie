package com.mlc.movie.controller;

import com.mlc.movie.model.Fan;
import com.mlc.movie.repository.FanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.mlc.movie.controller.Util.makeMap;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@RequestMapping("/api")
public class UserController {
    @Autowired
    FanRepository fanRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> addUser(@RequestParam String username, @RequestParam String password){

        if (username.isEmpty() || password.isEmpty()){
            return new ResponseEntity<>(makeMap("error", "Missing data"), HttpStatus.FORBIDDEN);
        }
        if(fanRepository.findByNickname(username) != null){
            return new ResponseEntity<>(makeMap("error", "Username already in use"), HttpStatus.FORBIDDEN);
        }

        fanRepository.save(new Fan(username, passwordEncoder.encode(password)));

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
