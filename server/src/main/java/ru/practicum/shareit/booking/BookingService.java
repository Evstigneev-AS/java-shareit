package ru.practicum.shareit.booking;

import ru.practicum.shareit.booking.dto.model.BookingIncDto;
import ru.practicum.shareit.booking.dto.model.BookingOutDto;

import java.util.List;

public interface BookingService {

    BookingOutDto createBooking(BookingIncDto bookingDto, Long userId);

    BookingOutDto approvedBooking(Long userId, Long bookingId, Boolean approved);

    BookingOutDto getBooking(Long userId, Long bookingId);

    List<BookingOutDto> getAllBookingsUser(Long userId, String state, Integer from, Integer size);

    List<BookingOutDto> getAllBookingsItemsUser(Long userId, String state, Integer from, Integer size);
}
