package com.example.discoveryclient.repository.impl;

import com.example.discoveryclient.model.Applicant;
import com.example.discoveryclient.repository.ApplicantRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class ApplicantRepositoryImpl implements ApplicantRepository {

    private final ApplicantDao applicantDAO;

    public ApplicantRepositoryImpl(ApplicantDao applicantDao) {
        this.applicantDAO = applicantDao;
    }

    @Override
    public Optional<Applicant> findById(long id) {
        return applicantDAO.findById(id);
    }

    @Override
    public Applicant save(Applicant applicant) {
        return applicantDAO.save(applicant);
    }

    @Override
    public void update(Applicant applicant) {
        applicantDAO.update(applicant);
    }

    @Override
    public void deleteById(long id) {
        applicantDAO.deleteById(id);
    }

    @Override
    public List<Applicant> findAll() {
        return applicantDAO.findAll();
    }
}
