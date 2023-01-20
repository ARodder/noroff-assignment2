/* Ensures that the table does not already exist when we try to
create it */
drop table if exists assistant;

/* Creates a table called assistant with a primary key called
id of type serial(autoincremented integer), and name-field of
type varchar(50) that can not be null*/
create table assistant (
	id serial primary key,
	name varchar(50) not null
);