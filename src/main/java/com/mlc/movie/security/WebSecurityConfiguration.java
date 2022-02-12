package com.mlc.movie.security;

import com.mlc.movie.model.userApp.UserApp;
import com.mlc.movie.repository.UserAppRepository;
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
    UserAppRepository userAppRepository;

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(inputName -> {
            UserApp userApp = userAppRepository.findByNickname(inputName);
            if (userApp != null) {
                if(Objects.equals(userApp.getNickname(), "admin")) {
                    return new User(userApp.getNickname(), userApp.getPassword(),
                            AuthorityUtils.createAuthorityList("ADMIN"));
                } else {
                    return new User(userApp.getNickname(), userApp.getPassword(),
                            AuthorityUtils.createAuthorityList("USER"));
                }
            } else {
                throw new UsernameNotFoundException("Unknown user: " + inputName);
            }
        });
    }
}
