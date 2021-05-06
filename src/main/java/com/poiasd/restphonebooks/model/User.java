package com.poiasd.restphonebooks.model;

import com.poiasd.restphonebooks.util.UidUtil;

public class User {
    public final String UID = UidUtil.uid();
    private String name;
    private PhoneBook phoneBook;

    public User(String name, PhoneBook phoneBook) {
        this.name = name;
        this.phoneBook = phoneBook;
    }

    // get set

    public String name() {
        return name;
    }

    public void name(String name) {
        this.name = name;
    }

    public PhoneBook phoneBook() {
        return phoneBook;
    }

    public void phoneBook(PhoneBook phoneBook) {
        this.phoneBook = phoneBook;
    }
}
