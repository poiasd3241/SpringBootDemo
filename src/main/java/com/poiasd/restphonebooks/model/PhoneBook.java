package com.poiasd.restphonebooks.model;

import com.poiasd.restphonebooks.util.UidUtil;

import java.util.List;

public class PhoneBook {
    public final String UID = UidUtil.uid();
    private List<Contact> contacts;

    public PhoneBook(List<Contact> contacts) {
        this.contacts = contacts;
    }

    // get set

    public List<Contact> contacts() {
        return contacts;
    }

    public void contacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

}
