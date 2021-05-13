package com.poiasd.restphonebooks.service;

import com.poiasd.restphonebooks.dto.mapper.ContactMapper;
import com.poiasd.restphonebooks.dto.model.ContactDTO;
import com.poiasd.restphonebooks.model.Contact;
import com.poiasd.restphonebooks.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The default implementation of the {@link ContactService}.
 * The {@link ContactRepository} is used as a subordinate link in the {@link ContactDTO} persistence.
 */
@Service
public class DefaultContactService implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public Optional<List<ContactDTO>> findByUserUID(String userUID) {
        Optional<List<Contact>> contacts = contactRepository.findByUserUID(userUID);
        return contacts.map(ContactMapper::toDtoList);
    }

    @Override
    public Optional<ContactDTO> findByPhoneNumber(String userUID, String phoneNumber) {
        Optional<Contact> contact = contactRepository.findByPhoneNumber(userUID, phoneNumber);
        return contact.map(ContactMapper::toDTO);
    }

    @Override
    public ContactDTO getByUID(String uid) {
        Contact contact = contactRepository.getByUID(uid);
        return ContactMapper.toDTO(contact);
    }

    @Override
    public void create(String userUID, ContactDTO contactDTO) {
        contactRepository.create(userUID, ContactMapper.fromDTO(contactDTO));
    }

    @Override
    public void update(String uid, ContactDTO contactDTO) {
        contactRepository.update(uid, ContactMapper.fromDTO(contactDTO));
    }

    @Override
    public void delete(String uid) {
        contactRepository.delete(uid);
    }
}

