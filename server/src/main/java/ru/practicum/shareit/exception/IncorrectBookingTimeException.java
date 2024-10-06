package ru.practicum.shareit.exception;

public class IncorrectBookingTimeException extends RuntimeException {
    public IncorrectBookingTimeException(final String message) {
        super(message);
    }
}