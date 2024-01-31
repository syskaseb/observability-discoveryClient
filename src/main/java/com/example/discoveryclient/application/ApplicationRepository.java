package com.example.discoveryclient.application;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface ApplicationRepository extends JpaRepository<Application, Long> {
}
