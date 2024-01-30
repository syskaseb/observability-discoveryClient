package com.example.discoveryclient.service.impl;

import com.example.discoveryclient.model.Applicant;
import com.example.discoveryclient.repository.ApplicantRepository;
import com.example.discoveryclient.service.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApplicantServiceImpl implements ApplicantService {

    private final ApplicantRepository applicantRepository;

    @Autowired
    public ApplicantServiceImpl(ApplicantRepository applicantRepository) {
        this.applicantRepository = applicantRepository;
    }

    @Override
    public List<Applicant> findAll() {
        return applicantRepository.findAll();
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
