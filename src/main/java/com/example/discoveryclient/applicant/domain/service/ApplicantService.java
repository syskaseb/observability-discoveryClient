package com.example.discoveryclient.applicant.domain.service;

import com.example.discoveryclient.applicant.application.dto.ApplicantDto;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface ApplicantService {

    ResponseEntity<PagedModel<EntityModel<ApplicantDto>>> getAllApplicants(Pageable pageable);


    ResponseEntity<EntityModel<ApplicantDto>> getApplicantById(Long id);

    ResponseEntity<EntityModel<ApplicantDto>> createApplicant(ApplicantDto applicantDto);

    ResponseEntity<EntityModel<ApplicantDto>> updateApplicant(@PathVariable Long id, @RequestBody ApplicantDto applicantDto);

    ResponseEntity<Object> deleteApplicant(@PathVariable Long id);
}
