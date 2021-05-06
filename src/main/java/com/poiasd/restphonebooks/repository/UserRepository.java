package com.poiasd.restphonebooks.repository;

import com.poiasd.restphonebooks.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<List<User>> findAll();

    Optional<List<User>> findByName(String name, boolean isFullName);

    User getByUID(String uid);

    void create(User user);

    void update(String uid, User user);

    void delete(String uid);
}
