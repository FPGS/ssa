create table IF NOT EXISTS USER(
	id bigint auto_increment PRIMARY KEY,
	name varchar(25) ,
	password varchar(25)
);
commit;

		merge into user key(id,name,password) values (1,'user','user');
		merge into user key(id,name,password) values (2,'admin','admin');
commit;


create table IF NOT EXISTS SNIPPET(
	id bigint auto_increment PRIMARY KEY,
	username SMALLINT ,
	text CLOB
);
commit;

create table IF NOT EXISTS BookTable(
	isbnOfBook varchar(25) PRIMARY KEY,
	titleOfBook varchar(25),
	nameOfAuthor varchar(50)
);
commit;

create table IF NOT EXISTS AuthorTable(
	nameOfAuthor varchar(50) PRIMARY KEY,
	dateOfBirth date
);
commit;
