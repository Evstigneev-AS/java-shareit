package ru.practicum.shareit.validation.exeption;

public class NotOwnerException extends RuntimeException {
    public NotOwnerException(final String message) {
        super(message);
    }
}
