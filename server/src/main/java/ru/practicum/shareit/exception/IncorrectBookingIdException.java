package ru.practicum.shareit.exception;

public class IncorrectBookingIdException extends RuntimeException {
    public IncorrectBookingIdException(final String message) {
        super(message);
    }
}