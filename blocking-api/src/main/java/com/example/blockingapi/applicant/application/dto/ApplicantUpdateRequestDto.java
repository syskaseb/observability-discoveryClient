package com.example.blockingapi.applicant.application.dto;

import com.example.blockingapi.applicant.infrastructure.entity.Applicant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class ApplicantUpdateRequestDto {

    private Long id;
    private String name;
    private String skills;

    public static ApplicantUpdateRequestDto fromEntity(Applicant applicant) {
        return ApplicantUpdateRequestDto.of(
                applicant.getId(),
                applicant.getName(),
                applicant.getSkills());
    }

    public Applicant toNewEntity() {
        return new Applicant(name, skills);
    }
}
