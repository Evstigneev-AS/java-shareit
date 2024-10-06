package ru.practicum.shareit.item.dto.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemIncDto {
    String name;
    String description;
    Boolean available;
    Long requestId;
}