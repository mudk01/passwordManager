create database if not exists passwordManager;

use passwordManager;

create table accounts (
    website varchar(50),
    email varchar(50),
    username varchar(50),
    password varchar(50),
    primary key (website)
);
