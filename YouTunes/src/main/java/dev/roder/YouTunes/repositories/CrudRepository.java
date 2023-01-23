package dev.roder.YouTunes.repositories;

import java.util.List;

/**
 * General interface for CRUD-repositories. Used to define
 * the common methods for all CRUD repositories.
 */
public interface CrudRepository<ID, T> {

    /**
     * Finds an instance in the database with the correct id.
     * 
     * @param id id of the object to find
     * @return Object with the corresponding id
     */
    T getById(ID id);

    /**
     * Retrieves all instances of the datatype T in the database.
     * 
     * @return List of all the objects in the database.
     */
    List<T> getAll();

    /**
     * Creates and adds a new instance of the object to the database.
     * 
     * @param object the new object to add
     */
    void create(T object);

    /**
     * Updates an instance of the object in the database.
     * 
     * @param object new values to update the instance with the same id to.
     */
    void update(T object);

    /**
     * Deletes an instance of the object in the database for a given id
     * 
     * @param id id of the object to delete
     */
    void delete(ID id);

}
