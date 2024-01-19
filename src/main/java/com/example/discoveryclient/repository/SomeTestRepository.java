package com.example.discoveryclient.repository;//package com.example.discoveryclient.repository;

import com.example.discoveryclient.model.SomeTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SomeTestRepository extends JpaRepository<SomeTest, Long> {
}
