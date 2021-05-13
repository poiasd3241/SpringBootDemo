package com.poiasd.restphonebooks.util;

import java.util.Objects;

/**
 * Provides the container for the result of data retrieval, either success or failure.
 *
 * @param <T> The type of data that is stored in a "success" container.
 */
public class Result<T> {
    private T data;
    private String failureMessage;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getFailureMessage() {
        return failureMessage;
    }

    public void setFailureMessage(String failureMessage) {
        this.failureMessage = failureMessage;
    }

    /**
     * Creates the "success" container, holding the specified data.
     * The {@link #failureMessage} is {@code null}.
     *
     * @param data The data to be stored.
     * @param <T>  The type of data to be stored.
     */
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setData(data);
        result.setFailureMessage(null);
        return result;
    }

    /**
     * Creates the "failure" container, holding the specified failure message.
     *
     * @param failureMessage The failure message.
     * @param <T>            The type of data that would be stored in a "success" container.
     */
    public static <T> Result<T> failure(String failureMessage) {
        Result<T> result = new Result<>();
        result.setData(null);
        result.setFailureMessage(failureMessage);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Result<?> result = (Result<?>) o;
        return Objects.equals(data, result.data) && Objects.equals(failureMessage, result.failureMessage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, failureMessage);
    }
}
