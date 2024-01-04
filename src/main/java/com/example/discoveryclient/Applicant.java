package com.example.discoveryclient;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name = "applicants")
@Data
public class Applicant implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255)
    private String name;

    @Column(length = 255)
    private String skills;

    public Applicant() {}

    // All-args constructor
    public Applicant(Long id, String name, String skills) {
        this.id = id;
        this.name = name;
        this.skills = skills;
    }
}
