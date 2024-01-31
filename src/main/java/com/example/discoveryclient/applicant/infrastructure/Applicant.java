package com.example.discoveryclient.applicant.infrastructure;

import com.example.discoveryclient.application.Application;
import jakarta.persistence.CascadeType;
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

    @OneToMany(mappedBy = "applicant", cascade = CascadeType.ALL, orphanRemoval = true)
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
