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

create table dict
(
    id        uuid       default gen_random_uuid() primary key,
    name      text,
    bcode     text,
    parent_id uuid references dict (id),
    deleted   numeric(1) default 0
);

create table module
(
    id    uuid default gen_random_uuid() primary key,
    title text not null,
    type  text not null
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

create table educational_program2module
(
    educational_program_id uuid references educational_program (id),
    module_id              uuid references module (id)
);

create table if not exists users
(
    id       uuid default gen_random_uuid() primary key,
    username varchar(30) not null,
    password varchar(128) default '{noop}123'
);

create table if not exists roles
(
    id uuid default gen_random_uuid() primary key,
    name varchar(20) not null
);
insert into roles(name) values ('USER');

create table if not exists user2role
(
    user_id uuid references users(id),
    role_id uuid references roles(id)
);

insert into users(username) values ('dima');

