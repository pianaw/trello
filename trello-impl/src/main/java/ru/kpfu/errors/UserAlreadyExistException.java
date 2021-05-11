package ru.kpfu.errors;

public class UserAlreadyExistException extends RuntimeException {

    public UserAlreadyExistException(final String message) {
        super(message);
    }
}
