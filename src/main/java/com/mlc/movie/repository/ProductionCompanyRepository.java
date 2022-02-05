package com.mlc.movie.repository;

import com.mlc.movie.model.productionCompany.ProductionCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProductionCompanyRepository extends JpaRepository<ProductionCompany, Long> {

}
