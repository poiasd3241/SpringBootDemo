package com.poiasd.restphonebooks.service;

import com.poiasd.restphonebooks.dto.model.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<List<UserDTO>> findAll();

    Optional<List<UserDTO>> findByName(String name, boolean isFullName);

    UserDTO getByUID(String uid);

    void create(UserDTO userDTO);

    void update(String uid, UserDTO userDTO);

    void delete(String uid);
}
