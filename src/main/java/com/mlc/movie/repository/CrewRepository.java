package com.mlc.movie.repository;

import com.mlc.movie.model.credit.crew.Crew;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * The CrewRepository class manages the persistence of the Crew Entity.
 */
@RepositoryRestResource
public interface CrewRepository extends JpaRepository<Crew, Long> {

}
