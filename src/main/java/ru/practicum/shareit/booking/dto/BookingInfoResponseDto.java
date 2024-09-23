package ru.practicum.shareit.booking.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import ru.practicum.shareit.booking.model.BookingStatus;
import ru.practicum.shareit.item.dto.ItemInfoResponseDto;
import ru.practicum.shareit.user.dto.UserInfoResponseDto;

import java.time.LocalDateTime;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingInfoResponseDto {
    Long id;
    ItemInfoResponseDto item;
    LocalDateTime start;
    LocalDateTime end;
    UserInfoResponseDto booker;
    BookingStatus status;
}
