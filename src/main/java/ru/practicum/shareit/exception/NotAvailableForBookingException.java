package ru.practicum.shareit.exception;

public class NotAvailableForBookingException extends RuntimeException {
    public NotAvailableForBookingException(final String message) {
        super(message);
    }
}
