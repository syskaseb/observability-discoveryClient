package com.example.blockingapi.applicant.infrastructure.repository;

import com.example.blockingapi.applicant.domain.repository.ApplicantRepository;
import com.example.blockingapi.applicant.infrastructure.repository.dao.ApplicantDao;
import com.example.blockingapi.applicant.infrastructure.entity.Applicant;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RepositoryRestResource
@Repository
public class ApplicantRepositoryImpl implements ApplicantRepository {

    private final ApplicantDao applicantDao;

    public ApplicantRepositoryImpl(ApplicantDao applicantDao) {
        this.applicantDao = applicantDao;
    }

    @Override
    public Page<Applicant> findAll(Pageable pageable) {
        return applicantDao.findAll(pageable);
    }

    @Override
    @Cacheable(value = "applicants", key = "#id")
    public Optional<Applicant> findById(long id) {
        return applicantDao.findById(id);
    }

    @Override
    @CachePut(value = "applicants", key = "#applicant.id")
    public Applicant save(Applicant applicant) {
        return applicantDao.save(applicant);
    }

    @Override
    @CachePut(value = "applicants", key = "#applicant.id")
    public void update(Applicant applicant) {
        applicantDao.update(applicant);
    }

    @Override
    @CacheEvict(value = "applicants", key = "#id")
    public void deleteById(long id) {
        applicantDao.deleteById(id);
    }
}
