package com.poiasd.restphonebooks.dto.mapper;

import com.poiasd.restphonebooks.dto.model.ContactDTO;
import com.poiasd.restphonebooks.model.Contact;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The test class for the {@link ContactMapper}.
 */
class ContactMapperTest {

    private final String contactName = "ContactOne";
    private final String phoneNumber = "+1";
    private final Contact contact = new Contact(contactName, phoneNumber);
    private final ContactDTO contactDTO = new ContactDTO(contactName, phoneNumber);
    private final List<Contact> contacts = List.of(contact);
    private final List<ContactDTO> contactsDTO = List.of(contactDTO);

    @DisplayName("Should return DTO model for the specified domain model.")
    @Test
    void toDTO() {
        // When
        var result = ContactMapper.toDTO(contact);

        // Then
        assertEquals(contactDTO, result);
    }

    @DisplayName("Should return domain model for the specified DTO model.")
    @Test
    void fromDTO() {
        // When
        var result = ContactMapper.fromDTO(contactDTO);

        // Then
        assertEquals(contact, result);
    }

    @DisplayName("Should return DTO model list for the specified domain model list.")
    @Test
    void toDtoList() {
        // When
        var result = ContactMapper.toDtoList(contacts);

        // Then
        assertEquals(contactsDTO, result);
    }

    @DisplayName("Should return domain model list for the specified DTO model list.")
    @Test
    void fromDtoList() {
        // When
        var result = ContactMapper.fromDtoList(contactsDTO);

        // Then
        assertEquals(contacts, result);
    }
}
