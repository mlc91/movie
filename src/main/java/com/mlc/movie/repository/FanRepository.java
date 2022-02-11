package com.mlc.movie.repository;

import com.mlc.movie.model.Fan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface FanRepository extends JpaRepository<Fan, Long> {
    Fan findByNickname(@Param("nickname") String nickname);
}
