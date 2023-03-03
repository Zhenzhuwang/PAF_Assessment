drop database if exists acme_bank;

create database acme_bank;

use acme_bank;

create table accounts (
account_id char(10) not null,
name char(64) not null,
balance decimal (19,2) not null,
primary key (account_id)
);

INSERT INTO accounts (account_id, name, balance) values ('V9L3Jd1BBI','fred','100');
INSERT INTO accounts (account_id, name, balance) values ('fhRq46Y6vB','barney','300');
INSERT INTO accounts (account_id, name, balance) values ('uFSFRqUpJy','wilma','1000');
INSERT INTO accounts (account_id, name, balance) values ('ckTV56axff','betty','1000');
INSERT INTO accounts (account_id, name, balance) values ('Qgcnwbshbh','pebbles','50');
INSERT INTO accounts (account_id, name, balance) values ('if9l185l18','bambam','50');