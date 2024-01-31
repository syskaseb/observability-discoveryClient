package com.example.discoveryclient.applicant.domain.repository;

import com.example.discoveryclient.applicant.infrastructure.Applicant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ApplicantRepository {

    Optional<Applicant> findById(long id);

    Applicant save(Applicant applicant);

    void update(Applicant applicant);

    void deleteById(long id);

    Page<Applicant> findAll(Pageable pageable);
}