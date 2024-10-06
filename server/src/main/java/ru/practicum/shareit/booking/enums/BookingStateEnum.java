package ru.practicum.shareit.booking.enums;

public enum BookingStateEnum {
    ALL,
    CURRENT,
    PAST,
    FUTURE,
    WAITING,
    REJECTED;

    public static BookingStateEnum from(String stringState) {
        for (BookingStateEnum state : values())
            if (state.name().equalsIgnoreCase(stringState))
                return state;

        return ALL;
    }
}