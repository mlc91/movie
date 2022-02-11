package com.mlc.movie.repository;

import com.mlc.movie.model.movie.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.awt.print.Pageable;
import java.util.List;

@RepositoryRestResource
public interface MovieRepository extends JpaRepository<Movie, Long> {
    Movie findByTmdbId(@Param("tmdbId") Long tmdbId);
}
