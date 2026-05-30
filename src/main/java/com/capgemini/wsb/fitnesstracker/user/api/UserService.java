package com.capgemini.wsb.fitnesstracker.user.api;

/**
 * Interface (API) for modifying operations on {@link User} entities through the API.
 * Implementing classes are responsible for executing changes within a database transaction, whether by continuing an existing transaction or creating a new one if required.
 */
public interface UserService {

    /**
     * Persists a new user in the system.
     *
     * @param user user entity to create
     * @return persisted user with generated identifier
     */
    User createUser(User user);

    /**
     * Updates an existing user identified by the given identifier.
     *
     * @param userId  identifier of the user to update
     * @param user    user data to apply
     * @return updated user entity
     */
    User updateUser(Long userId, User user);

    /**
     * Removes a user from the system.
     *
     * @param userId identifier of the user to delete
     */
    void deleteUser(Long userId);

}
