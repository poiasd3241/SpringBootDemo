package com.poiasd.restphonebooks.dto.model;

import com.poiasd.restphonebooks.util.UidUtil;

import java.util.List;

public class PhoneBookDTO {
    public final String UID = UidUtil.uid();
    private List<ContactDTO> contacts;

    public PhoneBookDTO(List<ContactDTO> contacts) {
        this.contacts = contacts;
    }

    // get set

    public List<ContactDTO> contacts() {
        return contacts;
    }

    public void contacts(List<ContactDTO> contacts) {
        this.contacts = contacts;
    }

}
