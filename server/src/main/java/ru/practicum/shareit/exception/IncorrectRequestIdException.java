package ru.practicum.shareit.exception;

public class IncorrectRequestIdException extends RuntimeException {
    public IncorrectRequestIdException(final String message) {
        super(message);
    }
}