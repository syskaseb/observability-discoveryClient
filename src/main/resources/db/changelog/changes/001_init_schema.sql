--liquibase formatted sql

--changeset syskas01:001_init_schema.sql
create table if not exists employer
(
    id       integer not null,
    name     varchar(255),
    industry varchar(255),
    constraint employers_pkey
        primary key (id)
);

create table if not exists applicant
(
    id     bigint not null,
    name   varchar(255),
    skills varchar(255),
    constraint applicants_pkey
        primary key (id)
);

create table if not exists job_offer
(
    id          integer not null,
    title       varchar(255),
    description varchar(255),
    location    varchar(255),
    salary_min  integer,
    salary_max  integer,
    employer_id integer,
    posted_date timestamp(6),
    constraint job_offers_pkey
        primary key (id),
    constraint fk1f415g3a9sgqbq00grclvn43t
        foreign key (employer_id) references employer
);

create table if not exists application
(
    id               integer not null,
    job_offer_id     integer,
    applicant_id     bigint,
    status           varchar(255),
    application_date timestamp(6),
    constraint applications_pkey
        primary key (id),
    constraint fkrc3gxkxtsq5jqx764drr3wug5
        foreign key (applicant_id) references applicant,
    constraint fkjp7yaago8i6ndvghp4ww8m6f1
        foreign key (job_offer_id) references job_offer
);

create table if not exists some_test
(
    id         integer not null,
    name       varchar(255),
    test_field varchar(255),
    primary key (id)
);
