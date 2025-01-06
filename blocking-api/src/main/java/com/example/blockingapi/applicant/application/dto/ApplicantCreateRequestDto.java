package com.example.blockingapi.applicant.application.dto;

import com.example.blockingapi.applicant.infrastructure.entity.Applicant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class ApplicantCreateRequestDto {

    private String name;
    private String skills;

    public Applicant toNewEntity() {
        return new Applicant(name, skills);
    }
}
