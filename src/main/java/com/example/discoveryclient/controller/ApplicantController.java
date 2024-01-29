package com.example.discoveryclient.controller;

import com.example.discoveryclient.model.Applicant;
import com.example.discoveryclient.service.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    private final ApplicantService applicantService;

    @Autowired
    public ApplicantController(ApplicantService applicantService) {
        this.applicantService = applicantService;
    }

    @GetMapping
    public ResponseEntity<List<Applicant>> getAllApplicants() {
        List<Applicant> applicants = applicantService.findAll();
        return ResponseEntity.ok(applicants);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Applicant> getApplicantById(@PathVariable Long id) {
        Optional<Applicant> applicant = applicantService.findById(id);
        return applicant.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Applicant> createApplicant(@RequestBody Applicant applicant) {
        Applicant savedApplicant = applicantService.save(applicant);
        return ResponseEntity.ok(savedApplicant);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateApplicant(@PathVariable Long id, @RequestBody Applicant applicant) {
        return applicantService.findById(id)
                .map(it -> {
                    Applicant updatedApplicant = new Applicant(
                            it.getId(),
                            it.getName(),
                            it.getSkills(),
                            it.getApplications()
                    );
                    applicantService.update(updatedApplicant);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteApplicant(@PathVariable Long id) {
        return applicantService.findById(id).map(it -> {
            applicantService.deleteById(it.getId());
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
