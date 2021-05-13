package com.poiasd.restphonebooks.exception;

import javax.validation.constraints.NotBlank;

/**
 * The object for testing where the validation of annotated fields is required ({@link NotBlank}).
 */
public class ValidatedObject {
    @NotBlank String notBlankField;

    public String getNotBlankField() {
        return notBlankField;
    }

    public void setField1(@NotBlank String notBlankField) {
        this.notBlankField = notBlankField;
    }

    public ValidatedObject(@NotBlank String notBlankField) {
        this.notBlankField = notBlankField;
    }

    public ValidatedObject() {
        this.notBlankField = "default";
    }
}
