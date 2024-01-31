package com.example.discoveryclient.sometest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface SomeTestRepository extends JpaRepository<SomeTest, Long> {
}
