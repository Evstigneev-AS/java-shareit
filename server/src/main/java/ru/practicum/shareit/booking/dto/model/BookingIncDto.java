package ru.practicum.shareit.booking.dto.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingIncDto {
    Long itemId;
    LocalDateTime start;
    LocalDateTime end;
}