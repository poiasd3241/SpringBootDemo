package com.poiasd.restphonebooks.service;

import com.poiasd.restphonebooks.dto.model.ContactDTO;

import java.util.List;
import java.util.Optional;

public interface ContactService {
    Optional<List<ContactDTO>> findAllByUserUID(String userUID);

    Optional<ContactDTO> findByPhoneNumber(String userUID, String phoneNumber);

    ContactDTO getByUID(String uid);

    void create(String userUID, ContactDTO contactDTO);

    void update(String uid, ContactDTO contactDTO);

    void delete(String uid);
}
