
/* We insert four rows into the power table with only
 the columns name and description specified, as the primary key
 id increments automatically because it is of data type serial*/
insert into power (name, description) values ('Super-stretch', 'Gives you the ability to arch your entire back and stretch rigorously to relieve stress, much like a dog does in the morning');
insert into power (name, description) values ('Feline instincts', 'Makes you scared of water and gives you the ability to draw upon the powers of catnip in combat');
insert into power (name, description) values ('Mega-mind', 'Awards you with a grotesquely enlarged cranium that gives you a major advantage when engaged in headbutting bouts');
insert into power (name, description) values ('Aquatic affinity', 'Makes you only able to consume and engage with water that has a salinity of at least 32‰');

/* We insert six rows into the linking table superhero_powers
 In the first three inserts, we add three different powers to
 the same single hero. In the latter three we add one single power
 to three different heroes, demonstrating the many-to-many
 relationship that exists between superhero and powers which is
 made possible by the linking table between them */

 /* one hero with many different powers */
insert into superhero_powers (power_id, superhero_id) values (1, 1);
insert into superhero_powers (power_id, superhero_id) values (3, 1);
insert into superhero_powers (power_id, superhero_id) values (4, 1);

/* one power awarded to many different heroes */
insert into superhero_powers (power_id, superhero_id) values (2, 1);
insert into superhero_powers (power_id, superhero_id) values (2, 2);
insert into superhero_powers (power_id, superhero_id) values (2, 3);
