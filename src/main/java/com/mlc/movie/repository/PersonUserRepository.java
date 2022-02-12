package com.mlc.movie.repository;

import com.mlc.movie.model.userApp.UserApp;
import com.mlc.movie.model.personUser.PersonUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * The PersonUserRepository class manages the persistence of the PersonUser Entity.
 */
@RepositoryRestResource
public interface PersonUserRepository extends JpaRepository<PersonUser, Long> {
    List<PersonUser> findByUserApp(@Param("userApp") UserApp userApp);
}
