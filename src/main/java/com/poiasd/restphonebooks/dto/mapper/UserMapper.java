package com.poiasd.restphonebooks.dto.mapper;

import com.poiasd.restphonebooks.dto.model.UserDTO;
import com.poiasd.restphonebooks.model.User;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Provides the functionality to map data between the {@link User} domain models and the {@link UserDTO} DTOs.
 */
public class UserMapper {
    public static UserDTO toDTO(User user) {
        return new UserDTO(user.getName(), PhoneBookMapper.toDTO(user.getPhoneBook()));
    }

    public static User fromDTO(UserDTO userDTO) {
        return new User(userDTO.getName(), PhoneBookMapper.fromDTO(userDTO.getPhoneBook()));
    }

    public static List<UserDTO> toDtoList(List<User> contacts) {
        return contacts.stream().map(UserMapper::toDTO).collect(Collectors.toList());
    }

    public static List<User> fromDtoList(List<UserDTO> contacts) {
        return contacts.stream().map(UserMapper::fromDTO).collect(Collectors.toList());
    }
}
