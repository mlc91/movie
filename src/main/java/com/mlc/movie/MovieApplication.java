package com.mlc.movie;

import com.mlc.movie.model.genre.Genre;
import com.mlc.movie.model.genre.GenreListDTO;
import com.mlc.movie.model.movie.Movie;
import com.mlc.movie.repository.GenreRepository;
import com.mlc.movie.repository.MovieRepository;
import com.mlc.movie.repository.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.stream.Collectors;

import static com.mlc.movie.model.genre.Genre.setGenreFromGenreDTO;
import static com.mlc.movie.model.genre.Genre.setGenresFromGenreListDTO;
import static com.mlc.movie.searchHelper.SearchHelper.getGenresFromAPI;

@SpringBootApplication
public class MovieApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(GenreRepository genreRepository) {
		return (args) -> {
			// TO PERSIST THE GENRE LIST FROM API
			GenreListDTO genreListDTO = getGenresFromAPI();
			genreRepository.saveAll(setGenresFromGenreListDTO(genreListDTO));
		};
	}
}
// todo: BORRAR TODOS LOS IMPORTS NO UTILIZADOS