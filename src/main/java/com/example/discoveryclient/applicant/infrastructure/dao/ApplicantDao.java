package com.example.discoveryclient.applicant.infrastructure.dao;

import com.example.discoveryclient.applicant.infrastructure.Applicant;

import java.util.List;
import java.util.Optional;

public interface ApplicantDao {
    List<Applicant> findAll();

    Optional<Applicant> findById(Long id);

    Applicant save(Applicant applicant);

    void update(Applicant applicant);

    void deleteById(Long id);
}
