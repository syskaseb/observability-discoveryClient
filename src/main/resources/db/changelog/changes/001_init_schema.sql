--liquibase formatted sql

--changeset syskas01:001_init_schema
CREATE TABLE IF NOT EXISTS job_offer
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

CREATE TABLE IF NOT EXISTS employer
(
    id       INT PRIMARY KEY,
    name     VARCHAR(255),
    industry VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS applicant
(
    id     INT PRIMARY KEY,
    name   VARCHAR(255),
    skills VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS application
(
    id               INT PRIMARY KEY,
    job_offer_id     INT,
    applicant_id     INT,
    status           VARCHAR(255),
    application_date DATE
);

CREATE TABLE IF NOT EXISTS some_test
(
    id     INT PRIMARY KEY,
    name   VARCHAR(255),
    test_field VARCHAR(255)
);
