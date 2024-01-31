package com.example.discoveryclient.applicant.application;

import com.example.discoveryclient.applicant.domain.service.ApplicantService;
import com.example.discoveryclient.applicant.infrastructure.Applicant;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
@RequestMapping("/applicants")
public class ApplicantController implements RepresentationModelProcessor<RepositoryLinksResource> {
    private static final String APPLICANTS_REL = "applicants";

    private final ApplicantService applicantService;

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<Applicant>>> getAllApplicants(
            @PageableDefault(size = 20) Pageable pageable
    ) {
        Page<Applicant> applicantPage = applicantService.findAll(pageable);
        List<EntityModel<Applicant>> applicantModels = applicantPage.getContent().stream()
                .map(applicant -> EntityModel.of(applicant,
                        linkTo(methodOn(ApplicantController.class).getApplicantById(applicant.getId())).withSelfRel(),
                        linkTo(ApplicantController.class).slash(applicant.getId()).withRel(APPLICANTS_REL)))
                .toList();

        PagedModel<EntityModel<Applicant>> pagedModel = PagedModel.of(applicantModels,
                new PagedModel.PageMetadata(
                        pageable.getPageSize(),
                        pageable.getPageNumber(),
                        applicantPage.getTotalElements(),
                        applicantPage.getTotalPages()));

        return ResponseEntity.ok(pagedModel);
    }

    @Override
    public RepositoryLinksResource process(RepositoryLinksResource resource) {
        Link applicantsLink = Link.of(
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
                        .methodOn(ApplicantController.class)
                        .getAllApplicants(null)) + "{?page,size,sort}",
                APPLICANTS_REL);

        resource.add(applicantsLink);
        return resource;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Applicant>> getApplicantById(@PathVariable Long id) {
        return applicantService.findById(id)
                .map(applicant -> EntityModel.of(applicant,
                        linkTo(methodOn(ApplicantController.class)
                                .getApplicantById(applicant.getId())).withSelfRel(),
                        linkTo(methodOn(ApplicantController.class)
                                .getAllApplicants(Pageable.ofSize(20))).withRel(APPLICANTS_REL)))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EntityModel<Applicant>> createApplicant(@RequestBody Applicant applicant) {
        Applicant savedApplicant = applicantService.save(applicant);
        return ResponseEntity.ok(EntityModel.of(savedApplicant,
                linkTo(methodOn(ApplicantController.class)
                        .getApplicantById(savedApplicant.getId())).withSelfRel()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Applicant>> updateApplicant(@PathVariable Long id, @RequestBody Applicant applicant) {
        return applicantService.findById(id)
                .map(it -> {
                    Applicant updatedApplicant = new Applicant(
                            it.getId(),
                            applicant.getName(),
                            applicant.getSkills(),
                            it.getApplications()
                    );
                    applicantService.update(updatedApplicant);
                    return EntityModel.of(updatedApplicant,
                            linkTo(methodOn(ApplicantController.class)
                                    .getApplicantById(updatedApplicant.getId())).withSelfRel());
                })
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteApplicant(@PathVariable Long id) {
        return applicantService.findById(id)
                .map(it -> {
                    applicantService.deleteById(it.getId());
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
