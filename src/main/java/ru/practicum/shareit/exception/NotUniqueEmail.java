package ru.practicum.shareit.exception;

public class NotUniqueEmail extends RuntimeException{
    public NotUniqueEmail(String message){
        super(String.format("The email = %s is not unique", message));
    }
}
