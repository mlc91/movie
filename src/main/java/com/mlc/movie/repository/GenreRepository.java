package com.mlc.movie.repository;

import com.mlc.movie.model.genre.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * The GenreRepository class manages the persistence of the Genre Entity.
 */
@RepositoryRestResource
public interface GenreRepository extends JpaRepository<Genre, Long> {

}
