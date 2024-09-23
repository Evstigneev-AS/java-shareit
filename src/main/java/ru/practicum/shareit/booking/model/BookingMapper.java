package ru.practicum.shareit.booking.model;

import lombok.experimental.UtilityClass;
import ru.practicum.shareit.booking.dto.BookingCreateRequestDto;
import ru.practicum.shareit.booking.dto.BookingInfoResponseDto;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.model.ItemMapper;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.model.UserMapper;

import java.util.List;

@UtilityClass
public class BookingMapper {
    public Booking toModel(final User booker, final Item item, final BookingCreateRequestDto bookingCreateRequestDto) {
        return Booking.builder()
                .start(bookingCreateRequestDto.getStart())
                .end(bookingCreateRequestDto.getEnd())
                .item(item)
                .booker(booker)
                .build();
    }


    public BookingInfoResponseDto toDto(final Booking booking) {
        return BookingInfoResponseDto.builder()
                .id(booking.getId())
                .start(booking.getStart())
                .end(booking.getEnd())
                .item(ItemMapper.toDto(booking.getItem()))
                .booker(UserMapper.toDto(booking.getBooker()))
                .status(booking.getBookingStatus())
                .build();
    }

    public List<BookingInfoResponseDto> toDto(final List<Booking> bookings) {
        return bookings.stream()
                .map(BookingMapper::toDto)
                .toList();
    }
}
