package com.example.blockingapi.applicant.infrastructure.repository.dao;

import com.example.blockingapi.applicant.infrastructure.entity.Applicant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ApplicantDao {
    Page<Applicant> findAll(Pageable pageable);

    Optional<Applicant> findById(Long id);

    Applicant save(Applicant applicant);

    void update(Applicant applicant);

    void deleteById(Long id);
}
