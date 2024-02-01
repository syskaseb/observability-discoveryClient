package com.example.discoveryclient.application;

import lombok.EqualsAndHashCode;

import java.sql.Timestamp;

public record ApplicationDto(
    Long id,
    @EqualsAndHashCode.Exclude
    String status,
    @EqualsAndHashCode.Exclude
    Timestamp applicationDate
) {
    public static ApplicationDto fromEntity(Application application) {
        return new ApplicationDto(
                application.getId(),
                application.getStatus(),
                application.getApplicationDate()
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ApplicationDto that = (ApplicationDto) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}