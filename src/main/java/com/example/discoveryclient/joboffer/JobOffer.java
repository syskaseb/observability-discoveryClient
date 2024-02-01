package com.example.discoveryclient.joboffer;

import com.example.discoveryclient.application.Application;
import com.example.discoveryclient.employer.Employer;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "job_offer")
@NamedEntityGraph(name = "JobOffer.withApplications",
        attributeNodes = @NamedAttributeNode(value = "applications"))
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JobOffer implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "location")
    private String location;

    @Column(name = "salary_min")
    private Integer salaryMin;

    @Column(name = "salary_max")
    private Integer salaryMax;

    @ManyToOne
    @JoinColumn(name = "employer_id")
    private Employer employer;

    @OneToMany(mappedBy = "jobOffer", cascade = CascadeType.PERSIST)
    private Set<Application> applications;

    @Column(name = "posted_date")
    private Timestamp postedDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JobOffer jobOffer = (JobOffer) o;

        return id.equals(jobOffer.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
