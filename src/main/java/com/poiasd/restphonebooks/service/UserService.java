package com.poiasd.restphonebooks.service;

import com.poiasd.restphonebooks.dto.model.UserDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service interface that is supposed to interact with some type of the {@link UserDTO} persistence.
 */
public interface UserService {
    /**
     * Returns all users.
     */
    Optional<List<UserDTO>> findAll();

    /**
     * Returns users matching the specified user name, either full or partial.
     *
     * @param name       The user name.
     * @param isFullName {@code false} if the user name is partial; otherwise, {@code true}.
     */
    Optional<List<UserDTO>> findByName(String name, boolean isFullName);

    /**
     * Returns the specified user.
     *
     * @param uid The user UID.
     */
    UserDTO getByUID(String uid);

    /**
     * Creates a new user using the specified user data.
     *
     * @param userDTO The user data to save.
     */
    void create(UserDTO userDTO);

    /**
     * Updates the specified user, using the specified updated user data.
     *
     * @param uid     The user UID.
     * @param userDTO The updated user data.
     */
    void update(String uid, UserDTO userDTO);

    /**
     * Deletes the specified user.
     *
     * @param uid The user UID.
     */
    void delete(String uid);
}
