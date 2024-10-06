package ru.practicum.shareit.exception;

public class FailApprovedBookingException extends RuntimeException {
    public FailApprovedBookingException(final String message) {
        super(message);
    }
}