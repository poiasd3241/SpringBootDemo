package com.poiasd.restphonebooks.dto.model;

import com.poiasd.restphonebooks.model.User;
import com.poiasd.restphonebooks.util.ModelUidBase;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * The data transfer object model for {@link User} objects.
 */
public class UserDTO extends ModelUidBase {
    @NotBlank
    private String name;
    @NotNull
    private PhoneBookDTO phoneBook;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PhoneBookDTO getPhoneBook() {
        return phoneBook;
    }

    public void setPhoneBook(PhoneBookDTO phoneBook) {
        this.phoneBook = phoneBook;
    }

    public UserDTO(@NotBlank String name, @NotNull PhoneBookDTO phoneBook) {
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
        UserDTO userDTO = (UserDTO) o;
        // Exclude UID.
        return Objects.equals(name, userDTO.name) && Objects.equals(phoneBook, userDTO.phoneBook);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUid(), name, phoneBook);
    }
}
