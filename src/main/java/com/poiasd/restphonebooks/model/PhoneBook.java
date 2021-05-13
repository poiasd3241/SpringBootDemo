package com.poiasd.restphonebooks.model;

import com.poiasd.restphonebooks.util.ModelUidBase;

import java.util.List;
import java.util.Objects;

/**
 * The domain model for phone book objects.
 */
public class PhoneBook extends ModelUidBase {
    private List<Contact> contacts;

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public PhoneBook(List<Contact> contacts) {
        this.contacts = contacts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PhoneBook phoneBook = (PhoneBook) o;
        // Exclude UID.
        return Objects.equals(contacts, phoneBook.contacts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUid(), contacts);
    }
}
