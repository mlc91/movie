package com.mlc.movie.repository;

import com.mlc.movie.model.credit.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * The CreditRepository class manages the persistence of the Credit Entity.
 */
@RepositoryRestResource
public interface CreditRepository extends JpaRepository<Credit, Long> {

}
