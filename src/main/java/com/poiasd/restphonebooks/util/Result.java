package com.poiasd.restphonebooks.util;

public class Result<T> {
    private T data;
    private String failMessage;

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.data(data);
        return result;
    }

    public static <T> Result<T> failure(String failMessage) {
        Result<T> result = new Result<>();
        result.failMessage(failMessage);
        return result;
    }

    public T data() {
        return data;
    }

    public void data(T data) {
        this.data = data;
    }

    public String failMessage() {
        return failMessage;
    }

    public void failMessage(String failMessage) {
        this.failMessage = failMessage;
    }
}
