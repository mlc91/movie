package com.mlc.movie.repository;

import com.mlc.movie.model.Fan;
import com.mlc.movie.model.MovieUser;
import com.mlc.movie.model.PersonUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface PersonUserRepository extends JpaRepository<PersonUser, Long> {
    List<PersonUser> findByFan(@Param("fan") Fan fan);
}
