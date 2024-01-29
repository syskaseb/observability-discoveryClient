package com.example.discoveryclient.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

import java.util.Set;

@Entity
@Table(name = "applicant")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@RepositoryRestResource
public class Applicant {

    public Applicant(long id, String name, String skills) {
        this.id = id;
        this.name = name;
        this.skills = skills;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "skills")
    private String skills;

    @OneToMany(mappedBy = "applicant")
    @Setter
    private Set<Application> applications;
}
