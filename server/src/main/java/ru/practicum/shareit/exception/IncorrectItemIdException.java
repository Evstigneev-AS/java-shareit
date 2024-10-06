package ru.practicum.shareit.exception;

public class IncorrectItemIdException extends RuntimeException {
    public IncorrectItemIdException(final String message) {
        super(message);
    }

}