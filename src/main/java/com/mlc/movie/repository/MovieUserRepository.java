package com.mlc.movie.repository;

import com.mlc.movie.model.movieUser.MovieUser;
import com.mlc.movie.model.userApp.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface MovieUserRepository extends JpaRepository<MovieUser, Long> {
    List<MovieUser> findByUserApp(@Param("userApp") UserApp userApp);
}
