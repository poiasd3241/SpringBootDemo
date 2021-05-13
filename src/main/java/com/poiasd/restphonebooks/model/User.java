package com.poiasd.restphonebooks.model;

import com.poiasd.restphonebooks.util.ModelUidBase;

import java.util.Objects;

/**
 * The domain model for user objects.
 */
public class User extends ModelUidBase {
    private String name;
    private PhoneBook phoneBook;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PhoneBook getPhoneBook() {
        return phoneBook;
    }

    public void setPhoneBook(PhoneBook phoneBook) {
        this.phoneBook = phoneBook;
    }

    public User(String name, PhoneBook phoneBook) {
        this.name = name;
        this.phoneBook = phoneBook;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        // Exclude UID.
        return Objects.equals(name, user.name) && Objects.equals(phoneBook, user.phoneBook);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUid(), name, phoneBook);
    }
}
