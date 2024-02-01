package com.example.discoveryclient.applicant.application.dto;

import com.example.discoveryclient.applicant.infrastructure.entity.Applicant;
import com.example.discoveryclient.application.ApplicationDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class ApplicantDto {

    private Long id;
    private String name;
    private String skills;
    private Set<ApplicationDto> applicationDtos;

    public static ApplicantDto fromEntity(Applicant applicant) {
        return ApplicantDto.of(
                applicant.getId(),
                applicant.getName(),
                applicant.getSkills(),
                applicant.getApplications().stream()
                        .map(ApplicationDto::fromEntity)
                        .collect(Collectors.toSet()));
    }

    public Applicant toNewEntity() {
        return new Applicant(name, skills);
    }
}

