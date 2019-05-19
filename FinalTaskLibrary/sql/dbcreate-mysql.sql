DROP DATABASE IF EXISTS library;
CREATE DATABASE librarydb CHARACTER SET utf8 COLLATE utf8_bin;

USE librarydb;

CREATE TABLE roles(
	id INTEGER NOT NULL PRIMARY KEY,
	name VARCHAR(10) NOT NULL UNIQUE
);

INSERT INTO roles VALUES(0, 'admin');
INSERT INTO roles VALUES(1, 'librarian');
INSERT INTO roles VALUES(2, 'user');

CREATE TABLE users(
	id INTEGER NOT NULL auto_increment PRIMARY KEY,
	login VARCHAR(20) NOT NULL UNIQUE,
	password VARCHAR(20) NOT NULL,
	first_name VARCHAR(20) NOT NULL,
	last_name VARCHAR(20) NOT NULL,
	role_id INTEGER NOT NULL REFERENCES roles(id) 

		ON DELETE CASCADE 
		ON UPDATE RESTRICT
);

INSERT INTO users VALUES(DEFAULT, 'admin', 'admin', 'Ivan', 'Ivanov', 0);
INSERT INTO users VALUES(DEFAULT, 'admin2', 'admin2', 'Petr', 'Petrov', 0);
INSERT INTO users VALUES(DEFAULT, 'librarian', 'librarian', 'Sergey', 'Sergeev', 1);
INSERT INTO users VALUES(DEFAULT, 'librarian2', 'librarian2', 'Alex', 'Alexandrov', 1);
INSERT INTO users VALUES(DEFAULT, 'user', 'user', 'Nikolay', 'Nikolaev', 2);
INSERT INTO users VALUES(DEFAULT, 'user2', 'user2', 'Tamara', 'Tamarovna', 2);
INSERT INTO users VALUES(DEFAULT, 'user3', 'user3', 'Zina', 'Zinoidovna', 2);

CREATE TABLE catalog(
	id INTEGER NOT NULL auto_increment PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	author VARCHAR(50) NOT NULL,
	edition VARCHAR(50) NOT NULL,
	year_edition INTEGER NOT NULL,
	count INTEGER NOT NULL
);

INSERT INTO catalog VALUES(DEFAULT, 'angels and demons', 'dan brown', 'pottermore limited', 2000, 5);
INSERT INTO catalog VALUES(DEFAULT, 'the da vinci code', 'dan brown', 'pottermore limited', 2003, 2);
INSERT INTO catalog VALUES(DEFAULT, 'inferno', 'dan brown', 'pottermore limited', 2013, 2);
INSERT INTO catalog VALUES(DEFAULT, 'harry potter and philosophers stone', 'joanne rowling', 'pocket books', 1997, 3);
INSERT INTO catalog VALUES(DEFAULT, 'harry potter and the chamber of secrets', 'joanne rowling', 'pocket books', 1998, 4);
INSERT INTO catalog VALUES(DEFAULT, 'harry potter and the prisoner of azkaban', 'joanne rowling', 'pocket books', 1999, 3);
INSERT INTO catalog VALUES(DEFAULT, 'harry potter and the goblet of fire', 'joanne rowling', 'pocket books', 2000, 3);
INSERT INTO catalog VALUES(DEFAULT, 'harry potter and the order of the phoenix', 'joanne rowling', 'pocket Books', 2003, 2);
INSERT INTO catalog VALUES(DEFAULT, 'harry potter and the half-blood prince', 'joanne rowling', 'pocket books', 2005, 5);
INSERT INTO catalog VALUES(DEFAULT, 'harry potter and the deathly hallows ', 'joanne rowling', 'pocket Books', 2007, 1);
INSERT INTO catalog VALUES(DEFAULT, 'the green mile', 'stephen king', '	signet books', 1996, 6);
INSERT INTO catalog VALUES(DEFAULT, 'stalker', 'strugatsky', 'macmillan', 1972, 8);
INSERT INTO catalog VALUES(DEFAULT, 'sherlock holmes', 'conan doyle', 'scarlet', 1904, 5);
INSERT INTO catalog VALUES(DEFAULT, 'the lord of the rings', 'tolkien', 'unwin', 1954, 3);
INSERT INTO catalog VALUES(DEFAULT, 'thinking in java', 'bruce eckel', 'neoclassic', 2009, 7);
INSERT INTO catalog VALUES(DEFAULT, 'oliver twist', 'charles dickens', 'bentleys miscellany', 1839, 1);

create table orders (
	id integer auto_increment primary key, 
	nameBook varchar(50) not null,  
	author varchar(50) not null,  
	edition varchar(50) not null,  
	year_edition integer(10) not null
);

INSERT INTO orders VALUES(DEFAULT, 'angels and demons', 'dan brown', 'pottermore limited', 2000, 'user');
INSERT INTO orders VALUES(DEFAULT, 'inferno', 'dan brown', 'pottermore limited', 2013, 'user2');
