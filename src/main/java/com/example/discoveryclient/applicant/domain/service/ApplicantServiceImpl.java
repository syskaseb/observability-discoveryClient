package com.example.discoveryclient.applicant.domain.service;

import com.example.discoveryclient.applicant.application.controller.ApplicantController;
import com.example.discoveryclient.applicant.application.dto.ApplicantDto;
import com.example.discoveryclient.applicant.domain.repository.ApplicantRepository;
import com.example.discoveryclient.applicant.infrastructure.entity.Applicant;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Slf4j
@RequiredArgsConstructor
@Service
public class ApplicantServiceImpl implements ApplicantService {

    private final ApplicantRepository applicantRepository;

    @Override
    @Retryable(retryFor = Exception.class, maxAttempts = 5, backoff = @Backoff(delay = 1000))
    @CircuitBreaker(name = "applicantService", fallbackMethod = "fallbackFindAll")
    public ResponseEntity<PagedModel<EntityModel<ApplicantDto>>> getAllApplicants(Pageable pageable) {
        Page<Applicant> applicantPage = applicantRepository.findAll(pageable);
        List<EntityModel<ApplicantDto>> applicantModels = applicantPage.getContent().stream()
                .map(ApplicantDto::fromEntity)
                .map(applicantDto -> EntityModel.of(applicantDto,
                        linkTo(methodOn(ApplicantController.class).getApplicantById(applicantDto.getId())).withSelfRel(),
                        linkTo(ApplicantController.class).slash(applicantDto.getId()).withRel("applicants")))
                .toList();

        PagedModel<EntityModel<ApplicantDto>> pagedModel = PagedModel.of(applicantModels,
                new PagedModel.PageMetadata(
                        pageable.getPageSize(),
                        pageable.getPageNumber(),
                        applicantPage.getTotalElements(),
                        applicantPage.getTotalPages()));

        return ResponseEntity.ok(pagedModel);
    }

    private List<Applicant> fallbackFindAll() {
        log.warn("service was not responsive when finding all applicants, fallback used");
        return Collections.emptyList();
    }


    @Override
    public ResponseEntity<EntityModel<ApplicantDto>> getApplicantById(Long id) {
        return applicantRepository.findById(id)
                .map(ApplicantDto::fromEntity)
                .map(applicant -> EntityModel.of(applicant,
                        linkTo(methodOn(ApplicantController.class)
                                .getApplicantById(applicant.getId())).withSelfRel(),
                        linkTo(methodOn(ApplicantController.class)
                                .getAllApplicants(Pageable.ofSize(20))).withRel("applicants")))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<EntityModel<ApplicantDto>> createApplicant(ApplicantDto applicantDto) {
        Applicant savedApplicant = applicantRepository.save(applicantDto.toNewEntity());
        return ResponseEntity.ok(EntityModel.of(applicantDto,
                linkTo(methodOn(ApplicantController.class)
                        .getApplicantById(savedApplicant.getId())).withSelfRel()));

    }

    @Override
    @Transactional
    public ResponseEntity<EntityModel<ApplicantDto>> updateApplicant(Long id, ApplicantDto applicantDto) {
        return applicantRepository.findById(id)
                .map(it -> {
                    Applicant updatedApplicant = it.setSkills(applicantDto.getSkills());
                    applicantRepository.update(updatedApplicant);
                    return EntityModel.of(applicantDto,
                            linkTo(methodOn(ApplicantController.class)
                                    .getApplicantById(updatedApplicant.getId())).withSelfRel());
                })
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Object> deleteApplicant(Long id) {
        return applicantRepository.findById(id)
                .map(it -> {
                    applicantRepository.deleteById(it.getId());
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
