package com.mlc.movie.repository;

import com.mlc.movie.model.productionCountry.ProductionCountry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProductionCountryRepository extends JpaRepository<ProductionCountry, Long> {

}
