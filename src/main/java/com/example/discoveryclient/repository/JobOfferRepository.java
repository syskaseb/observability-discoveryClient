package com.example.discoveryclient.repository;

import com.example.discoveryclient.model.JobOffer;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface JobOfferRepository extends JpaRepository<JobOffer, Long> {

    @Override
    @EntityGraph(value = "JobOffer.withApplications", type = EntityGraph.EntityGraphType.FETCH)
    List<JobOffer> findAll();
}
