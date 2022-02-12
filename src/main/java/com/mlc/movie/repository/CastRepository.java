package com.mlc.movie.repository;

import com.mlc.movie.model.credit.cast.Cast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * The CastRepository class manages the persistence of the Cast Entity.
 */
@RepositoryRestResource
public interface CastRepository extends JpaRepository<Cast, Long> {

}