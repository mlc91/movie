package com.mlc.movie.repository;

import com.mlc.movie.model.genre.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface GenreRepository extends JpaRepository<Genre, Long> {

}
