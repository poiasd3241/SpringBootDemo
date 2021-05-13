package com.poiasd.restphonebooks.dto.model;

import com.poiasd.restphonebooks.model.PhoneBook;
import com.poiasd.restphonebooks.util.ModelUidBase;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

/**
 * The data transfer object model for {@link PhoneBook} objects.
 */
public class PhoneBookDTO extends ModelUidBase {
    @NotNull
    private List<ContactDTO> contacts;

    public List<ContactDTO> getContacts() {
        return contacts;
    }

    public void setContacts(List<ContactDTO> contacts) {
        this.contacts = contacts;
    }

    public PhoneBookDTO(List<ContactDTO> contacts) {
        this.contacts = contacts;
    }

    public PhoneBookDTO() {
        this.contacts = List.of();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PhoneBookDTO that = (PhoneBookDTO) o;
        // Exclude UID.
        return Objects.equals(contacts, that.contacts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUid(), contacts);
    }
}
