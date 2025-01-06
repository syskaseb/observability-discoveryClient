package com.example.blockingapi.applicant.application.dto;

import com.example.blockingapi.applicant.infrastructure.entity.Applicant;
import com.example.blockingapi.application.ApplicationDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class ApplicantResponseDto {

    private Long id;
    private String name;
    private String skills;
    private Set<ApplicationDto> applicationDtos;

    public static ApplicantResponseDto fromEntity(Applicant applicant) {
        return ApplicantResponseDto.of(
                applicant.getId(),
                applicant.getName(),
                applicant.getSkills(),
                applicant.getApplications().stream()
                        .map(ApplicationDto::fromEntity)
                        .collect(Collectors.toSet()));
    }
}

