package com.mlc.movie.repository;

import com.mlc.movie.model.person.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PersonRepository extends JpaRepository<Person, Long> {
    Person findByTmdbId(@Param("tmdbId") Long tmdbId);
}