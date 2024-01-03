--liquibase formatted sql

--changeset syskas01:change-application-date-to-timestamp
ALTER TABLE job_offers
ALTER COLUMN posted_date TYPE TIMESTAMP;