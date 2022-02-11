package com.mlc.movie.repository;

import com.mlc.movie.model.Fan;
import com.mlc.movie.model.PersonUser;
import com.mlc.movie.model.person.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Set;

@RepositoryRestResource
public interface PersonRepository extends JpaRepository<Person, Long> {
    Person findByTmdbId(@Param("tmdbId") Long tmdbId);
}