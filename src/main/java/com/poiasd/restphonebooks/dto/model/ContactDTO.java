package com.poiasd.restphonebooks.dto.model;

import com.poiasd.restphonebooks.model.Contact;
import com.poiasd.restphonebooks.util.ModelUidBase;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

/**
 * The data transfer object model for {@link Contact} objects.
 */
public class ContactDTO extends ModelUidBase {
    @NotBlank
    private String name;
    @NotBlank
    private String phoneNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public ContactDTO(@NotBlank String name, @NotBlank String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ContactDTO that = (ContactDTO) o;
        // Exclude UID.
        return Objects.equals(name, that.name) && Objects.equals(phoneNumber, that.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUid(), name, phoneNumber);
    }
}
