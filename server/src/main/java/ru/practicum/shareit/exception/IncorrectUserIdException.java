package ru.practicum.shareit.exception;

public class IncorrectUserIdException extends RuntimeException {
    public IncorrectUserIdException(final String message) {
        super(message);
    }
}