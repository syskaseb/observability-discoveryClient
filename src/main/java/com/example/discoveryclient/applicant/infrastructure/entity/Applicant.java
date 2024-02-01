package com.example.discoveryclient.applicant.infrastructure.entity;

import com.example.discoveryclient.application.Application;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "applicant")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@RepositoryRestResource
public class Applicant implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "skills")
    private String skills;

    public Applicant setSkills(String skills) {
        this.skills = skills;
        return this;
    }

    public Applicant(String name, String skills) {
        this.name = name;
        this.skills = skills;
    }

    public Applicant(Long id, String name, String skills) {
        this.id = id;
        this.name = name;
        this.skills = skills;
    }

    @OneToMany(mappedBy = "applicant", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @Setter
    private Set<Application> applications;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Applicant applicant = (Applicant) o;

        return getId().equals(applicant.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
