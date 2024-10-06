package ru.practicum.shareit.exception;

public class IncorrectCommentatorException extends RuntimeException {
    public IncorrectCommentatorException(final String message) {
        super(message);
    }
}