package com.example.discoveryclient.service;

import com.example.discoveryclient.model.Applicant;
import com.example.discoveryclient.repository.ApplicantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApplicantService {

    private final ApplicantRepository applicantRepository;

    @Autowired
    public ApplicantService(ApplicantRepository applicantRepository) {
        this.applicantRepository = applicantRepository;
    }

    public List<Applicant> findAll() {
        return applicantRepository.findAll();
    }

    public Optional<Applicant> findById(Long id) {
        return applicantRepository.findById(id);
    }

    public Applicant save(Applicant applicant) {
        return applicantRepository.save(applicant);
    }

    public void update(Applicant applicant) {
        applicantRepository.update(applicant);
    }

    public void deleteById(Long id) {
        applicantRepository.deleteById(id);
    }
}
