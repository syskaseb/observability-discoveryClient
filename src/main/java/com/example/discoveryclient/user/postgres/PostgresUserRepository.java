package com.example.discoveryclient.user.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface PostgresUserRepository extends JpaRepository<PostgresUser, Long> {
    PostgresUser findByUsername(String username);
}
