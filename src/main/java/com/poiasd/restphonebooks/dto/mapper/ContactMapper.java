package com.poiasd.restphonebooks.dto.mapper;

import com.poiasd.restphonebooks.dto.model.ContactDTO;
import com.poiasd.restphonebooks.model.Contact;

import java.util.List;
import java.util.stream.Collectors;

public class ContactMapper {
    public static ContactDTO toDTO(Contact contact) {
        return new ContactDTO(contact.name(), contact.phoneNumber());
    }

    public static Contact fromDTO(ContactDTO contactDTO) {
        return new Contact(contactDTO.name(), contactDTO.phoneNumber());
    }

    public static List<ContactDTO> toDtoList(List<Contact> contacts) {
        return contacts.stream().map(ContactMapper::toDTO).collect(Collectors.toList());
    }

    public static List<Contact> fromDtoList(List<ContactDTO> contacts) {
        return contacts.stream().map(ContactMapper::fromDTO).collect(Collectors.toList());
    }
}
