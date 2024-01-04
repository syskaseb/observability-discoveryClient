--liquibase formatted sql

--changeset syskas01:rename-tables-to-singulars
ALTER TABLE applicants RENAME TO applicant;
ALTER TABLE applications RENAME TO application;
ALTER TABLE employers RENAME TO employer;
ALTER TABLE job_offers RENAME TO job_offer;

--rollback ALTER TABLE applicant RENAME TO applicants;
--rollback ALTER TABLE application RENAME TO applications;
--rollback ALTER TABLE employer RENAME TO employers;
--rollback ALTER TABLE job_offer RENAME TO job_offers;
