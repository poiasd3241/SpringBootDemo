package com.poiasd.restphonebooks.dto.mapper;

import com.poiasd.restphonebooks.dto.model.PhoneBookDTO;
import com.poiasd.restphonebooks.model.Contact;
import com.poiasd.restphonebooks.model.PhoneBook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The test class for the {@link PhoneBookMapper}.
 */
class PhoneBookMapperTest {

    private final Contact contact = new Contact("ContactOne", "+1");
    private final List<Contact> contacts = List.of(contact);
    private final PhoneBook phoneBook = new PhoneBook(contacts);
    private final PhoneBookDTO phoneBookDTO = PhoneBookMapper.toDTO(phoneBook);

    @DisplayName("Should return DTO model for the specified domain model.")
    @Test
    void toDTO() {
        // When
        var result = PhoneBookMapper.toDTO(phoneBook);

        // Then
        assertEquals(phoneBookDTO, result);
    }

    @DisplayName("Should return domain model for the specified DTO model.")
    @Test
    void fromDTO() {
        // When
        var result = PhoneBookMapper.fromDTO(phoneBookDTO);

        // Then
        assertEquals(phoneBook, result);
    }
}
