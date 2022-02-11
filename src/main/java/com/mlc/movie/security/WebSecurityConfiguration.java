package com.mlc.movie.security;

import com.mlc.movie.model.Fan;
import com.mlc.movie.repository.FanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Objects;

@Configuration
public class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {

    @Autowired
    FanRepository fanRepository;

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(inputName -> {
            Fan fan = fanRepository.findByNickname(inputName);
            if (fan != null) {
                if(Objects.equals(fan.getNickname(), "admin")) {
                    return new User(fan.getNickname(), fan.getPassword(),
                            AuthorityUtils.createAuthorityList("ADMIN"));
                } else {
                    return new User(fan.getNickname(), fan.getPassword(),
                            AuthorityUtils.createAuthorityList("USER"));
                }
            } else {
                throw new UsernameNotFoundException("Unknown user: " + inputName);
            }
        });
    }
}
