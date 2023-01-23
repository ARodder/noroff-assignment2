/* Ensures that the tables do not already exist when we try to
create them */
drop table if exists superhero;
drop table if exists assistant;
drop table if exists power;


/* Creates a table called superhero with a primary key called
id of type serial(autoincremented integer), name-field of
type varchar(50) than cannot be null, alias of type varchar(50)
that cannot be null as well as origin of type varchar(255)
that cannot be null */
create table superhero (
    id serial primary key,
    name varchar(50) not null,
    alias varchar(50) not null,
    origin varchar(255) not null
);


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