package ru.practicum.shareit.booking.dto.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.shareit.booking.enums.BookingStatusEnum;
import ru.practicum.shareit.item.dto.model.ItemOutDto;
import ru.practicum.shareit.user.dto.UserDto;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingOutDto {
    Long id;
    LocalDateTime start;
    LocalDateTime end;
    ItemOutDto item;
    UserDto booker;
    Enum<BookingStatusEnum> status;
}