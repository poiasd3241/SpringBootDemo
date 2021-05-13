package com.poiasd.restphonebooks.exception;

/**
 * Runtime exceptions that are supposed to be thrown by repositories
 * (controller -> service -> repository! -> DAO(database/other).
 */
public class EntityRuntimeExceptions {

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
