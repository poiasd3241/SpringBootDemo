package com.poiasd.restphonebooks.service;

import com.poiasd.restphonebooks.dto.mapper.UserMapper;
import com.poiasd.restphonebooks.dto.model.UserDTO;
import com.poiasd.restphonebooks.model.User;
import com.poiasd.restphonebooks.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The default implementation of the {@link UserService}.
 * The {@link UserRepository} is used as a subordinate link in the {@link UserDTO} persistence.
 */
@Service
public class DefaultUserService implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<List<UserDTO>> findAll() {
        Optional<List<User>> users = userRepository.findAll();
        return users.map(UserMapper::toDtoList);
    }

    @Override
    public Optional<List<UserDTO>> findByName(String name, boolean isFullName) {
        Optional<List<User>> users = userRepository.findByName(name, isFullName);
        return users.map(UserMapper::toDtoList);
    }

    @Override
    public UserDTO getByUID(String uid) {
        User user = userRepository.getByUID(uid);
        return UserMapper.toDTO(user);
    }

    @Override
    public void create(UserDTO userDTO) {
        userRepository.create(UserMapper.fromDTO(userDTO));
    }

    @Override
    public void update(String uid, UserDTO userDTO) {
        userRepository.update(uid, UserMapper.fromDTO(userDTO));
    }

    @Override
    public void delete(String uid) {
        userRepository.delete(uid);
    }
}
