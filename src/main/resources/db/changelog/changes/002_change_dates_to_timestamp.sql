--liquibase formatted sql

--changeset syskas01:002_change_dates_to_timestamp
ALTER TABLE application
ALTER COLUMN application_date TYPE TIMESTAMP;

ALTER TABLE job_offer
ALTER COLUMN posted_date TYPE TIMESTAMP;
