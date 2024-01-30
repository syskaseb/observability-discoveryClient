package com.example.discoveryclient.repository.impl;

import com.example.discoveryclient.model.Applicant;
import com.example.discoveryclient.repository.ApplicantRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
    @Cacheable(value = "applicants", key = "#id")
    public Optional<Applicant> findById(long id) {
        return applicantDAO.findById(id);
    }

    @Override
    @CachePut(value = "applicants", key = "#applicant.id")
    public Applicant save(Applicant applicant) {
        return applicantDAO.save(applicant);
    }

    @Override
    @CachePut(value = "applicants", key = "#applicant.id")
    public void update(Applicant applicant) {
        applicantDAO.update(applicant);
    }

    @Override
    @CacheEvict(value = "applicants", key = "#id")
    public void deleteById(long id) {
        applicantDAO.deleteById(id);
    }

    @Override
    @Cacheable("applicants")
    public List<Applicant> findAll() {
        return applicantDAO.findAll();
    }
}
