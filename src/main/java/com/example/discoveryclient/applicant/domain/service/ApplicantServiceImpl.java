package com.example.discoveryclient.applicant.domain.service;

import com.example.discoveryclient.applicant.domain.repository.ApplicantRepository;
import com.example.discoveryclient.applicant.infrastructure.Applicant;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ApplicantServiceImpl implements ApplicantService {

    private final ApplicantRepository applicantRepository;

    @Override
    @Retryable(retryFor = Exception.class, maxAttempts = 5, backoff = @Backoff(delay = 1000))
    @CircuitBreaker(name = "applicantService", fallbackMethod = "fallbackFindAll")
    public Page<Applicant> findAll(Pageable pageable) {
        return applicantRepository.findAll(pageable);
    }

    private List<Applicant> fallbackFindAll() {
        log.warn("service was not responsive when finding all applicants, fallback used");
        return Collections.emptyList();
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
