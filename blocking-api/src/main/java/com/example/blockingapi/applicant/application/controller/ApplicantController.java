package com.example.blockingapi.applicant.application.controller;

import com.example.blockingapi.applicant.application.dto.ApplicantCreateUpdateRequestDto;
import com.example.blockingapi.applicant.application.dto.ApplicantResponseDto;
import com.example.blockingapi.applicant.domain.service.ApplicantService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.RepositoryLinksResource;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/applicants")
public class ApplicantController implements RepresentationModelProcessor<RepositoryLinksResource> {

    private final ApplicantService applicantService;

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<ApplicantResponseDto>>> getAllApplicants(
            @PageableDefault(size = 20) Pageable pageable
    ) {
        return applicantService.getAllApplicants(pageable);
    }

    @Override
    public RepositoryLinksResource process(RepositoryLinksResource resource) {
        Link applicantsLink = Link.of(
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
                        .methodOn(ApplicantController.class)
                        .getAllApplicants(null)) + "{?page,size,sort}",
                "applicants");

        resource.add(applicantsLink);
        return resource;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<ApplicantResponseDto>> getApplicantById(@PathVariable Long id) {
        return applicantService.getApplicantById(id);
    }

    @PostMapping
    public ResponseEntity<EntityModel<ApplicantResponseDto>> createApplicant(@RequestBody ApplicantCreateUpdateRequestDto applicantDto) {
        return applicantService.createApplicant(applicantDto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EntityModel<ApplicantResponseDto>> updateApplicant(@PathVariable Long id, @RequestBody ApplicantCreateUpdateRequestDto applicantDto) {
        return applicantService.updateApplicant(id, applicantDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteApplicant(@PathVariable Long id) {
        return applicantService.deleteApplicant(id);
    }
}
