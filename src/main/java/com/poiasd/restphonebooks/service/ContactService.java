package com.poiasd.restphonebooks.service;

import com.poiasd.restphonebooks.dto.model.ContactDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service interface that is supposed to interact with some type of the {@link ContactDTO} persistence.
 */
public interface ContactService {
    /**
     * Returns all contacts of the specified user.
     *
     * @param userUID The user UID.
     */
    Optional<List<ContactDTO>> findByUserUID(String userUID);

    /**
     * Returns a contact matching the specified phone number and belongs to the specified user.
     *
     * @param userUID     The user UID.
     * @param phoneNumber The phone number.
     */
    Optional<ContactDTO> findByPhoneNumber(String userUID, String phoneNumber);

    /**
     * Returns the the specified contact.
     *
     * @param uid The contact UID.
     */
    ContactDTO getByUID(String uid);

    /**
     * Creates a new contact for the specified user, using the specified contact data.
     *
     * @param userUID    The user UID.
     * @param contactDTO The contact data to save.
     */
    void create(String userUID, ContactDTO contactDTO);

    /**
     * Updates the specified contact, using the specified updated contact data.
     *
     * @param uid        The contact UID.
     * @param contactDTO The updated contact data.
     */
    void update(String uid, ContactDTO contactDTO);

    /**
     * Deletes the specified contact.
     *
     * @param uid The contact UID.
     */
    void delete(String uid);
}
