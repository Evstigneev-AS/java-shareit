package ru.practicum.shareit.request.dto.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.shareit.item.dto.model.ItemToRequestDto;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RequestWithItemDto {
    Long id;
    String description;
    LocalDateTime created;
    List<ItemToRequestDto> items;
}
