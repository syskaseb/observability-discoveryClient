package com.example.discoveryclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ApplicantService {

    @Autowired
    private ApplicantRepository applicantRepository;

    public List<Applicant> findAll() {
        return applicantRepository.findAll();
    }

    public Optional<Applicant> findById(Long id) {
        return applicantRepository.findById(id);
    }

    public Applicant save(Applicant applicant) {
        return applicantRepository.save(applicant);
    }

    public Applicant update(Long id, Applicant applicantDetails) {
        return applicantRepository.findById(id)
                .map(applicant -> {
                    applicant.setName(applicantDetails.getName());
                    applicant.setSkills(applicantDetails.getSkills());
                    return applicantRepository.save(applicant);
                })
                .orElseGet(() -> {
                    applicantDetails.setId(id);
                    return applicantRepository.save(applicantDetails);
                });
    }

    public void delete(Long id) {
        applicantRepository.deleteById(id);
    }
}
