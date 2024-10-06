package ru.practicum.shareit.exception;

public class ItemAvailableException extends RuntimeException {
    public ItemAvailableException(final String message) {
        super(message);
    }
}