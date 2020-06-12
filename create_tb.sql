CREATE DATABASE IF NOT EXISTS featuredb;
USE featuredb;
CREATE TABLE IF NOT EXISTS feature ( 
    id smallint unsigned not null auto_increment,
    name varchar(50) not null,
   PRIMARY KEY(id));
CREATE TABLE IF NOT EXISTS cmd ( 
    id smallint unsigned not null auto_increment,
    name varchar(50) not null,
    fea_id smallint unsigned,
    FOREIGN KEY (fea_id) REFERENCES feature(id),
    PRIMARY KEY(id));   
CREATE TABLE IF NOT EXISTS subcmd ( 
    id smallint unsigned not null auto_increment,
    name varchar(50) not null,
    cmd_id smallint unsigned,
    FOREIGN KEY (cmd_id) REFERENCES cmd(id),
    PRIMARY KEY(id));   
CREATE TABLE IF NOT EXISTS param ( 
    id smallint unsigned not null auto_increment,
    name varchar(50) not null,
    subcmd_id smallint unsigned,
    FOREIGN KEY (subcmd_id) REFERENCES subcmd(id),
    PRIMARY KEY(id));   