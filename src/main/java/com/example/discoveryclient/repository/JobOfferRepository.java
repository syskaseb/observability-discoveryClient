package com.example.discoveryclient.repository;

import com.example.discoveryclient.model.JobOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface JobOfferRepository extends JpaRepository<JobOffer, Long> {
}
