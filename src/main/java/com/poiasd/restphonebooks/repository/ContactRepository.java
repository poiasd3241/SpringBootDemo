package com.poiasd.restphonebooks.repository;

import com.poiasd.restphonebooks.model.Contact;

import java.util.List;
import java.util.Optional;

public interface ContactRepository {
    Optional<List<Contact>> findAllByUserUID(String userUID);

    Optional<Contact> findByPhoneNumber(String userUID, String phoneNumber);

    Contact getByUID(String uid);

    void create(String userUID, Contact contact);

    void update(String uid, Contact contact);

    void delete(String uid);
}
