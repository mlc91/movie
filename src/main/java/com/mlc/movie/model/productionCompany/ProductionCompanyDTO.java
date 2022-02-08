package com.mlc.movie.model.productionCompany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mlc.movie.model.movie.Movie;
import com.mlc.movie.model.movie.MovieDTO;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true) //this anotation is in case the api changes
public class ProductionCompanyDTO {
    private Long id;
    @JsonProperty("logo_path")
    private String logoPath;
    private String name;
    @JsonProperty("origin_country")
    private String originCountry;

    public static ProductionCompany setPCompanyFromPCompanyDTO(ProductionCompanyDTO pCompanyDTO) {
        ProductionCompany pCompany = new ProductionCompany();
        pCompany.setId(pCompanyDTO.getId());
        pCompany.setLogoPath(pCompanyDTO.getLogoPath());
        pCompany.setName(pCompanyDTO.getName());
        pCompany.setOriginCountry(pCompanyDTO.getOriginCountry());
        return pCompany;
    }
}
