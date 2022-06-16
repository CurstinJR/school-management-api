/**
 * @Author Curstin Rose - 220275408
 * Created on 10 June 2022
 * IService.java
 */
package za.ac.cput.school_management_grp33.service;

import java.util.List;
import java.util.Optional;

/**
 * Generic service interface to be used on the domain-specific
 * interfaces.
 * @param <T> Domain Model
 * @param <ID> Identifier of the model
 */
public interface IService<T, ID> {

    /**
     * Saves object of specified type to the repository.
     * @param t Domain Model Type
     * @return The object type specified
     */
    T save(T t);

    /**
     * Retrieves a list of the object types.
     * @return List of all object types specified
     */
    List<T> findAll();

    /**
     * Retrieves the object type that is equal to the input identifier.
     * @param id The object type identifier
     * @return The object type specified
     */
    Optional<T> findById(ID id);

    /**
     * Removes the object type from the repository.
     * @param t Domain Model Type
     */
    void deleteById(ID id);
}
