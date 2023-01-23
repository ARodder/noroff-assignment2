
/*
Creates a linking-table for holding relations between 
superheroes and their powers. It requires its own table
because many a superhero can have multiple powers and
the same power can be found in multiple superheroes.
power_id is an integer foreign key that refernces the 
power-table. Superhero_id is an integer foreign key
that references the superhero-table. The primary key 
is a composite key of the two foreign keys.
*/
create table superhero_powers(
	power_id int references power,
	superhero_id int references superhero,
	primary key(power_id,superhero_id)
);