

/* We insert three rows into the superhero database
   with values for all columns except id as this is a primary key
   of type serial that will automatically increment with new entries
 */
insert into superhero (name, alias, origin) values ('Bruce Wayne', 'Batman', 'Had en extensive career in the MLB');
insert into superhero (name, alias, origin) values ('Tony Stark', 'Ironman', 'Had severe iron-deficiency as a child');
insert into superhero (name, alias, origin) values ('Diana', 'Wonder Woman', 'As a child she spent her days wondering about the ways of the world');