package com.poiasd.restphonebooks.service;

import com.poiasd.restphonebooks.dto.mapper.ContactMapper;
import com.poiasd.restphonebooks.dto.model.ContactDTO;
import com.poiasd.restphonebooks.model.Contact;
import com.poiasd.restphonebooks.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class DefaultContactService implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public Optional<List<ContactDTO>> findAllByUserUID(String userUID) {
        Optional<List<Contact>> contacts = contactRepository.findAllByUserUID(userUID);
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

