/* Ensures that the tables does not already exist when we try to
create them */
drop table if exists assistant;
drop table if exists power;

/* Creates a table called assistant with a primary key called
id of type serial(autoincremented integer), and name-field of
type varchar(50) that can not be null*/
create table assistant (
	id serial primary key,
	name varchar(50) not null
);

/* Creates a table called power with a primary key called id
of type serial(autoincremented integer), a name-field of type
varchar(50) that can not be null, and a description field of
type varchar(255) since this can be a longer text and can not be null*/
create table power (
	id serial primary key,
	name varchar(50) not null,
	description varchar(255) not null
);