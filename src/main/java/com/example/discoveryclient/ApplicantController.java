package com.example.discoveryclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/applicants")
public class ApplicantController {

    @Autowired
    private ApplicantService applicantService; // Assuming ApplicantService is implemented

    @GetMapping
    public List<Applicant> getAllApplicants() {
        return applicantService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Applicant> getApplicantById(@PathVariable Long id) {
        return applicantService.findById(id);
    }

    @PostMapping
    public Applicant createApplicant(@RequestBody Applicant applicant) {
        return applicantService.save(applicant);
    }

    @PutMapping("/{id}")
    public Applicant updateApplicant(@PathVariable Long id, @RequestBody Applicant applicantDetails) {
        return applicantService.update(id, applicantDetails); // Implement update logic in the service
    }

    @DeleteMapping("/{id}")
    public void deleteApplicant(@PathVariable Long id) {
        applicantService.delete(id);
    }
}
