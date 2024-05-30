create table institute
(
    id    uuid default gen_random_uuid() primary key,
    title text not null
);

create table head
(
    id       uuid default gen_random_uuid() primary key,
    fullname text not null
);

create table module
(
    id    uuid default gen_random_uuid() primary key,
    title text not null,
    type  uuid references dict (id)
);

create table dict
(
    id        uuid       default gen_random_uuid() primary key,
    name      text,
    bcode     text,
    parent_id uuid references dict (id),
    deleted   numeric(1) default 0
);

create table educational_program
(
    id                uuid default gen_random_uuid() primary key,
    title             text        not null,
    cypher            varchar(30) not null,
    level_id          uuid references dict (id),
    standard_id       uuid references dict (id),
    institute         uuid references institute (id),
    head              uuid references head (id),
    accreditationTime date        not null
);