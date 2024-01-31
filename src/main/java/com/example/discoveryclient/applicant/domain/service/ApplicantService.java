package com.example.discoveryclient.applicant.domain.service;

import com.example.discoveryclient.applicant.infrastructure.Applicant;

import java.util.List;
import java.util.Optional;

public interface ApplicantService {
    List<Applicant> findAll();

    Optional<Applicant> findById(Long id);

    Applicant save(Applicant applicant);

    void update(Applicant applicant);

    void deleteById(Long id);
}
