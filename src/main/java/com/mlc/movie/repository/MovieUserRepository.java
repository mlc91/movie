package com.mlc.movie.repository;

import com.mlc.movie.model.MovieUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface MovieUserRepository extends JpaRepository<MovieUser, Long> {
    MovieUser findByUsername(@Param("username") String username);
}