create database bankdb;

use bankdb;

create table accounts(
     id int primary key AUTO_INCREMENT,
     name varchar(50),
     account_no int UNIQUE,
     balance double
);