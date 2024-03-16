package com.example.blockingapi.user.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;


@RepositoryRestResource
public interface PostgresUserRepository extends JpaRepository<PostgresUser, Long> {
    Optional<PostgresUser> findByUsername(String username);
}
