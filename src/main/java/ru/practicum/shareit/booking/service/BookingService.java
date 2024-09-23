package ru.practicum.shareit.booking.service;

import org.springframework.data.domain.Pageable;
import ru.practicum.shareit.booking.dto.BookingCreateRequestDto;
import ru.practicum.shareit.booking.dto.BookingInfoResponseDto;

import java.util.List;

public interface BookingService {
    BookingInfoResponseDto create(final long userId, final BookingCreateRequestDto bookingCreateRequestDto);

    BookingInfoResponseDto approve(final long userId, final long bookingId, final boolean isApproved);

    BookingInfoResponseDto getByBookingId(final long userId, final long bookingId);

    List<BookingInfoResponseDto> getBookings(final long userId, final String bookingState, final Pageable pageable);

    List<BookingInfoResponseDto> getBookingsByOwner(final long ownerId, final String bookingState,
                                                    final Pageable pageable);
}
