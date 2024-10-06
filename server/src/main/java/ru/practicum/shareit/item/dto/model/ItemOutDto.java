package ru.practicum.shareit.item.dto.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.shareit.request.dto.model.RequestOutDto;
import ru.practicum.shareit.user.dto.UserDto;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemOutDto {
    Long id;
    String name;
    String description;
    UserDto owner;
    Integer numberOfRentals;
    Boolean available;
    RequestOutDto request;
    List<CommentOutDto> comments;
}