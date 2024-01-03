--liquibase formatted sql

--changeset syskas01:initial-schema
CREATE TABLE job_offers
(
    id          INT PRIMARY KEY,
    title       VARCHAR(255),
    description TEXT,
    location    VARCHAR(255),
    salary_min  INT,
    salary_max  INT,
    employer_id INT,
    posted_date DATE
);

CREATE TABLE employers
(
    id       INT PRIMARY KEY,
    name     VARCHAR(255),
    industry VARCHAR(255)
);

CREATE TABLE applicants
(
    id     INT PRIMARY KEY,
    name   VARCHAR(255),
    skills VARCHAR(255)
);

CREATE TABLE applications
(
    id               INT PRIMARY KEY,
    job_offer_id     INT,
    applicant_id     INT,
    status           VARCHAR(255),
    application_date DATE
);

--rollback DROP TABLE applications;
--rollback DROP TABLE applicants;
--rollback DROP TABLE employers;
--rollback DROP TABLE job_offers;