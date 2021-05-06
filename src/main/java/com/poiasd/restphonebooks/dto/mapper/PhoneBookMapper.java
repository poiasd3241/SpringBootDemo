package com.poiasd.restphonebooks.dto.mapper;

import com.poiasd.restphonebooks.dto.model.PhoneBookDTO;
import com.poiasd.restphonebooks.model.PhoneBook;

public class PhoneBookMapper {
    public static PhoneBookDTO toDTO(PhoneBook phoneBook) {
        return new PhoneBookDTO(ContactMapper.toDtoList(phoneBook.contacts()));
    }

    public static PhoneBook fromDTO(PhoneBookDTO phoneBookDTO) {
        return new PhoneBook(ContactMapper.fromDtoList(phoneBookDTO.contacts()));
    }
}
