package com.poiasd.restphonebooks.dto.mapper;

import com.poiasd.restphonebooks.dto.model.PhoneBookDTO;
import com.poiasd.restphonebooks.model.PhoneBook;

/**
 * Provides the functionality to map data between the {@link PhoneBook} domain models and the {@link PhoneBookDTO} DTOs.
 */
public class PhoneBookMapper {
    public static PhoneBookDTO toDTO(PhoneBook phoneBook) {
        return new PhoneBookDTO(ContactMapper.toDtoList(phoneBook.getContacts()));
    }

    public static PhoneBook fromDTO(PhoneBookDTO phoneBookDTO) {
        return new PhoneBook(ContactMapper.fromDtoList(phoneBookDTO.getContacts()));
    }
}
