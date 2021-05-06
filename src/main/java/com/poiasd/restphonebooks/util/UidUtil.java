package com.poiasd.restphonebooks.util;

import java.security.SecureRandom;
import java.util.Base64;

public class UidUtil {
    private static final SecureRandom random = new SecureRandom();
    private static final Base64.Encoder encoder = Base64.getEncoder();


    /**
     * @return A random 16-byte string encoded using Base64.
     */
    public static String uid() {
        byte[] buffer = new byte[16];
        random.nextBytes(buffer);
        return encoder.encodeToString(buffer);
    }
}
