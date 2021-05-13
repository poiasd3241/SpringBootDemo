package com.poiasd.restphonebooks.util;

import java.security.SecureRandom;
import java.util.Base64;

/**
 * Provides "unique ID" functionality for use to uniquely identify the app's objects during runtime.<br/>
 * Has no relation to the IDs of objects used by data persistence.
 */
public class UidUtil {
    private static final SecureRandom random = new SecureRandom();
    private static final Base64.Encoder encoder = Base64.getEncoder();

    /**
     * @return A string representation of a Base64-encoded random byte[15] array.
     */
    public static String uid() {
        byte[] buffer = new byte[15];
        random.nextBytes(buffer);
        return encoder.encodeToString(buffer);
    }
}
