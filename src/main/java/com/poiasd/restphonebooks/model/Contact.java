package com.poiasd.restphonebooks.model;

import com.poiasd.restphonebooks.util.UidUtil;

public class Contact {
    public final String UID = UidUtil.uid();
    private String name;
    private String phoneNumber;

    public Contact(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    // get set

    public String name() {
        return name;
    }

    public void name(String name) {
        this.name = name;
    }

    public String phoneNumber() {
        return phoneNumber;
    }

    public void phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
