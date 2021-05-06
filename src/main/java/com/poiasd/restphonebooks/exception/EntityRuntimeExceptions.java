package com.poiasd.restphonebooks.exception;

import com.poiasd.restphonebooks.model.Contact;
import com.poiasd.restphonebooks.model.PhoneBook;
import com.poiasd.restphonebooks.model.User;

/**
 * Runtime exceptions defined here are supposed to be thrown in repositories.
 */
public class EntityRuntimeExceptions {

    public static RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String conditions) {
        return switch (exceptionType) {
            case DUPLICATE -> new DuplicateEntityException(getMessage(entityType, conditions, "already exists."));
            case NOT_FOUND -> new EntityNotFoundException(getMessage(entityType, conditions, "doesn't exist."));
        };
    }

    private static String getMessage(EntityType entityType, String conditions, String exceptionTypeMessage) {
        return entityType.value() + " with " + conditions + " " + exceptionTypeMessage;
    }

    public enum EntityType {
        CONTACT(Contact.class.getName()),
        PHONEBOOK(PhoneBook.class.getName()),
        USER(User.class.getName());

        String value;

        EntityType(String value) {
            this.value = value;
        }

        String value() {
            return this.value;
        }
    }

    public enum ExceptionType {
        DUPLICATE,
        NOT_FOUND
    }

    public static class EntityNotFoundException extends RuntimeException {
        public EntityNotFoundException(String message) {
            super(message);
        }
    }

    public static class DuplicateEntityException extends RuntimeException {
        public DuplicateEntityException(String message) {
            super(message);
        }
    }
}
