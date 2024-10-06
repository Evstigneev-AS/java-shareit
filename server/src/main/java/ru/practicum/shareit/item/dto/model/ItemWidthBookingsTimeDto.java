package ru.practicum.shareit.item.dto.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.shareit.booking.dto.model.BookingWithItemsDto;
import ru.practicum.shareit.request.dto.model.RequestOutDto;
import ru.practicum.shareit.user.dto.UserDto;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemWidthBookingsTimeDto {
    Long id;
    String name;
    String description;
    UserDto owner;
    Integer numberOfRentals;
    Boolean available;
    RequestOutDto request;
    BookingWithItemsDto lastBooking;
    BookingWithItemsDto nextBooking;
    List<CommentOutDto> comments;
}
