--liquibase formatted sql

--changeset syskas01:change-application-date-to-timestamp
ALTER TABLE applications
ALTER COLUMN application_date TYPE TIMESTAMP;