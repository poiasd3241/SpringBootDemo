package com.poiasd.restphonebooks.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The test class for the {@link UidUtil}.
 */
class UidUtilTest {

    @DisplayName("Should return the string representation of a Base64-encoded random byte[15] array.")
    @Test
    void uid() {
        // Given
        Pattern pattern = Pattern.compile("[a-z0-9+/]{20}", Pattern.CASE_INSENSITIVE);

        // When
        var result1 = pattern.matcher(UidUtil.uid());
        var result2 = pattern.matcher(UidUtil.uid());

        // Then
        assertTrue(result1.matches());
        assertTrue(result2.matches());
        assertNotEquals(result1, result2);

        // Not sure how many checks is enough for the test...
        for (int i = 0; i < 1000; i++) {
            assertTrue(pattern.matcher(UidUtil.uid()).matches());
        }
    }
}
