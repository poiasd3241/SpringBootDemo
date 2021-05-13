package com.poiasd.restphonebooks.dto.mapper;

import com.poiasd.restphonebooks.dto.model.PhoneBookDTO;
import com.poiasd.restphonebooks.dto.model.UserDTO;
import com.poiasd.restphonebooks.model.Contact;
import com.poiasd.restphonebooks.model.PhoneBook;
import com.poiasd.restphonebooks.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The test class for the {@link UserMapper}.
 */
class UserMapperTest {

    private final PhoneBook phoneBook = new PhoneBook(
            List.of(new Contact("ContactOne", "+1")));
    private final PhoneBookDTO phoneBookDTO = PhoneBookMapper.toDTO(phoneBook);

    private final String userName = "UserOne";
    private final User user = new User(userName, phoneBook);
    private final UserDTO userDTO = new UserDTO(userName, phoneBookDTO);
    private final List<User> users = List.of(user);
    private final List<UserDTO> usersDTO = List.of(userDTO);

    @DisplayName("Should return DTO model for the specified domain model.")
    @Test
    void toDTO() {
        // When
        var result = UserMapper.toDTO(user);

        // Then
        assertEquals(userDTO, result);
    }

    @DisplayName("Should return domain model for the specified DTO model.")
    @Test
    void fromDTO() {
        // When
        var result = UserMapper.fromDTO(userDTO);

        // Then
        assertEquals(user, result);
    }

    @DisplayName("Should return DTO model list for the specified domain model list.")
    @Test
    void toDtoList() {
        // When
        var result = UserMapper.toDtoList(users);

        // Then
        assertEquals(usersDTO, result);
    }

    @DisplayName("Should return domain model list for the specified DTO model list.")
    @Test
    void fromDtoList() {
        // When
        var result = UserMapper.fromDtoList(usersDTO);

        // Then
        assertEquals(users, result);
    }
}
