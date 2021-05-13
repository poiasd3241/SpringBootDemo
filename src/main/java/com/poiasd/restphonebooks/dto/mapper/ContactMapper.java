package com.poiasd.restphonebooks.dto.mapper;

import com.poiasd.restphonebooks.dto.model.ContactDTO;
import com.poiasd.restphonebooks.model.Contact;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Provides the functionality to map data between the {@link Contact} domain models and the {@link ContactDTO} DTOs.
 */
public class ContactMapper {
    public static ContactDTO toDTO(Contact contact) {
        return new ContactDTO(contact.getName(), contact.getPhoneNumber());
    }

    public static Contact fromDTO(ContactDTO contactDTO) {
        return new Contact(contactDTO.getName(), contactDTO.getPhoneNumber());
    }

    public static List<ContactDTO> toDtoList(List<Contact> contacts) {
        return contacts.stream().map(ContactMapper::toDTO).collect(Collectors.toList());
    }

    public static List<Contact> fromDtoList(List<ContactDTO> contacts) {
        return contacts.stream().map(ContactMapper::fromDTO).collect(Collectors.toList());
    }
}
