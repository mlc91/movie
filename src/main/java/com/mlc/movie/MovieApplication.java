package com.mlc.movie;

import com.mlc.movie.model.userApp.UserApp;
import com.mlc.movie.repository.UserAppRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class MovieApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieApplication.class, args);
	}

	@Bean
	public PasswordEncoder passwordEncoder(){
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Bean
	public CommandLineRunner initData(UserAppRepository userAppRepository) {
		return (args) -> {
			UserApp userApp = userAppRepository.save(new UserApp("admin", passwordEncoder().encode("123")));
			UserApp userApp1 = userAppRepository.save(new UserApp("raqui", passwordEncoder().encode("123")));
		};
	}
}
// todo: BORRAR TODOS LOS IMPORTS NO UTILIZADOS

