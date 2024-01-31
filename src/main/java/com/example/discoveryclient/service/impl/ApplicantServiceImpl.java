package com.example.discoveryclient.service.impl;

import com.example.discoveryclient.model.Applicant;
import com.example.discoveryclient.repository.ApplicantRepository;
import com.example.discoveryclient.service.ApplicantService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ApplicantServiceImpl implements ApplicantService {

    private final ApplicantRepository applicantRepository;

    @Autowired
    public ApplicantServiceImpl(ApplicantRepository applicantRepository) {
        this.applicantRepository = applicantRepository;
    }

    @Override
    @Retryable(retryFor = Exception.class, maxAttempts = 3, backoff = @Backoff(delay = 1000))
    @CircuitBreaker(name = "applicantService", fallbackMethod = "fallbackFindAll")
    public List<Applicant> findAll() {
        return applicantRepository.findAll();
    }

    private List<Applicant> fallbackFindAll() {
        log.warn("service not responsive on finding all applicants, fallback used");
        return new ArrayList<>();
    }

    @Override
    public Optional<Applicant> findById(Long id) {
        return applicantRepository.findById(id);
    }

    @Override
    public Applicant save(Applicant applicant) {
        return applicantRepository.save(applicant);
    }

    @Override
    public void update(Applicant applicant) {
        applicantRepository.update(applicant);
    }

    @Override
    public void deleteById(Long id) {
        applicantRepository.deleteById(id);
    }
}
