package com.example.blockingapi.applicant.domain.service;

import com.example.blockingapi.applicant.application.dto.ApplicantResponseDto;
import com.example.blockingapi.applicant.application.dto.ApplicantCreateUpdateRequestDto;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface ApplicantService {

    ResponseEntity<PagedModel<EntityModel<ApplicantResponseDto>>> getAllApplicants(Pageable pageable);


    ResponseEntity<EntityModel<ApplicantResponseDto>> getApplicantById(Long id);

    ResponseEntity<EntityModel<ApplicantResponseDto>> createApplicant(ApplicantCreateUpdateRequestDto applicantDto);

    ResponseEntity<EntityModel<ApplicantResponseDto>> updateApplicant(@PathVariable Long id, @RequestBody ApplicantCreateUpdateRequestDto applicantDto);

    ResponseEntity<Object> deleteApplicant(@PathVariable Long id);
}
