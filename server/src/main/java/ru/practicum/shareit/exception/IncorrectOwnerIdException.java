package ru.practicum.shareit.exception;

public class IncorrectOwnerIdException extends RuntimeException {
    public IncorrectOwnerIdException(final String message) {
        super(message);
    }

}