package com.example.blockingapi.joboffer;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


@RepositoryRestResource
public interface JobOfferRepository extends JpaRepository<JobOffer, Long> {

    @Override
    @EntityGraph(value = "JobOffer.withApplications", type = EntityGraph.EntityGraphType.FETCH)
    List<JobOffer> findAll();
}
