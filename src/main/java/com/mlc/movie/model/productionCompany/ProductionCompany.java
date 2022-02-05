package com.mlc.movie.model.productionCompany;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ProductionCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long productionCompanyId;
    private int id;
    private String logoPath;
    private String name;
    private String originCountry;

    public ProductionCompany() {
    }
}
