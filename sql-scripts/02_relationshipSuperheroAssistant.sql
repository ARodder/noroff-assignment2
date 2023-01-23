

/* We alter the table assistant by adding
 the field superhero_id which will act as the
 foreign key and then we add the constraint that
 ensures superhero_id references the primary key
 of the superhero table*/
alter table assistant
    add column superhero_id int
        constraint fk_assistant_superhero references superhero(id);