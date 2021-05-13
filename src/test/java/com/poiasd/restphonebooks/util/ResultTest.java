package com.poiasd.restphonebooks.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * The test class for the {@link Result}.
 */
class ResultTest {

    @DisplayName("Should set the data to the specified value and set the failure message to null")
    @Test
    void success() {
        // Given
        var data = new Object();

        // When
        var result = Result.success(data);

        // Then
        assertEquals(data, result.getData());
        assertNull(result.getFailureMessage());
    }

    @DisplayName("Should set the data to null and set the failure message to the specified value.")
    @Test
    void failure() {
        // Given
        var failureMessage = "oops!";

        // When
        var result = Result.failure(failureMessage);

        // Then
        assertEquals(failureMessage, result.getFailureMessage());
        assertNull(result.getData());
    }
}
