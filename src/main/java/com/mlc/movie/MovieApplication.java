package com.mlc.movie;

import com.mlc.movie.model.movie.Movie;
import com.mlc.movie.repository.MovieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MovieApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(MovieRepository movieRepository) {
		return (args) -> {
			movieRepository.save(new Movie());
		};
	}

}
