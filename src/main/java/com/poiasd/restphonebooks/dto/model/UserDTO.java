package com.poiasd.restphonebooks.dto.model;

import com.poiasd.restphonebooks.util.UidUtil;

public class UserDTO {
    public final String UID = UidUtil.uid();
    private String name;
    private PhoneBookDTO phoneBook;

    public UserDTO(String name, PhoneBookDTO phoneBook) {
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

    public PhoneBookDTO phoneBook() {
        return phoneBook;
    }

    public void phoneBook(PhoneBookDTO phoneBook) {
        this.phoneBook = phoneBook;
    }
}
