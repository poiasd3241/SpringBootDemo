package com.poiasd.restphonebooks.repository;

import com.poiasd.restphonebooks.model.Contact;

import java.util.List;
import java.util.Optional;

/**
 * The interface that is supposed to interact with some type of the {@link Contact} persistence.
 */
public interface ContactRepository {
    /**
     * Returns all contacts of the specified user.
     *
     * @param userUID The user UID.
     */
    Optional<List<Contact>> findByUserUID(String userUID);

    /**
     * Returns a contact matching the specified phone number and belongs to the specified user.
     *
     * @param userUID     The user UID.
     * @param phoneNumber The phone number.
     */
    Optional<Contact> findByPhoneNumber(String userUID, String phoneNumber);

    /**
     * Returns the the specified contact.
     *
     * @param uid The contact UID.
     */
    Contact getByUID(String uid);

    /**
     * Creates a new contact for the specified user, using the specified contact data.
     *
     * @param userUID The user UID.
     * @param contact The contact data to save.
     */
    void create(String userUID, Contact contact);

    /**
     * Updates the specified contact, using the specified updated contact data.
     *
     * @param uid     The contact UID.
     * @param contact The updated contact data.
     */
    void update(String uid, Contact contact);

    /**
     * Deletes the specified contact.
     *
     * @param uid The contact UID.
     */
    void delete(String uid);
}
