package com.mlc.movie.repository;

import com.mlc.movie.model.userApp.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface UserAppRepository extends JpaRepository<UserApp, Long> {
    UserApp findByNickname(@Param("nickname") String nickname);
}
