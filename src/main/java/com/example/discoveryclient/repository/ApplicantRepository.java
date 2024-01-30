package com.example.discoveryclient.repository;

import com.example.discoveryclient.model.Applicant;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface ApplicantRepository {

    Optional<Applicant> findById(long id);
    Applicant save(Applicant applicant);
    void update(Applicant applicant);
    void deleteById(long id);
    List<Applicant> findAll();
}