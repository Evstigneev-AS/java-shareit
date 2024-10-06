package ru.practicum.shareit.exception;

public class FailCreateBookingOwnerItem extends RuntimeException {
    public FailCreateBookingOwnerItem(final String message) {
        super(message);
    }
}