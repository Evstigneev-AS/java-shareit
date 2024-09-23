package ru.practicum.shareit.item.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import ru.practicum.shareit.booking.dto.BookingInfoResponseDto;

import java.util.List;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemFullInfoResponseDto {
    long id;
    String name;
    String description;
    boolean available;
    List<CommentInfoResponseDto> comments;
    BookingInfoResponseDto lastBooking;
    BookingInfoResponseDto nextBooking;
}
