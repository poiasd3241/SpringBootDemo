package com.poiasd.restphonebooks.dto.mapper;

import com.poiasd.restphonebooks.dto.model.UserDTO;
import com.poiasd.restphonebooks.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {
    public static UserDTO toDTO(User user) {
        return new UserDTO(user.name(), PhoneBookMapper.toDTO(user.phoneBook()));
    }

    public static User fromDTO(UserDTO userDTO) {
        return new User(userDTO.name(), PhoneBookMapper.fromDTO(userDTO.phoneBook()));
    }

    public static List<UserDTO> toDtoList(List<User> contacts) {
        return contacts.stream().map(UserMapper::toDTO).collect(Collectors.toList());
    }

    public static List<User> fromDtoList(List<UserDTO> contacts) {
        return contacts.stream().map(UserMapper::fromDTO).collect(Collectors.toList());
    }
}
