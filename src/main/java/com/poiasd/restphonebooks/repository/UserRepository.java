package com.poiasd.restphonebooks.repository;

import com.poiasd.restphonebooks.model.User;

import java.util.List;
import java.util.Optional;

/**
 * The interface that is supposed to interact with some type of the {@link User} persistence.
 */
public interface UserRepository {
    /**
     * Returns all users.
     */
    Optional<List<User>> findAll();

    /**
     * Returns users matching the specified user name, either full or partial.
     *
     * @param name       The user name.
     * @param isFullName {@code false} if the user name is partial; otherwise, {@code true}.
     */
    Optional<List<User>> findByName(String name, boolean isFullName);

    /**
     * Returns the specified user.
     *
     * @param uid The user UID.
     */
    User getByUID(String uid);

    /**
     * Creates a new user using the specified user data.
     *
     * @param user The user data to save.
     */
    void create(User user);

    /**
     * Updates the specified user, using the specified updated user data.
     *
     * @param uid  The user UID.
     * @param user The updated user data.
     */
    void update(String uid, User user);

    /**
     * Deletes the specified user.
     *
     * @param uid The user UID.
     */
    void delete(String uid);
}
