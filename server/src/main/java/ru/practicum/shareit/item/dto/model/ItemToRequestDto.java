package ru.practicum.shareit.item.dto.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemToRequestDto {
    Long id;
    String name;
    Long owner;
}
