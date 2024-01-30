package com.example.discoveryclient.service;

import com.example.discoveryclient.model.Applicant;

import java.util.List;
import java.util.Optional;

public interface ApplicantService {
    List<Applicant> findAll();

    Optional<Applicant> findById(Long id);

    Applicant save(Applicant applicant);

    void update(Applicant applicant);

    void deleteById(Long id);
}
