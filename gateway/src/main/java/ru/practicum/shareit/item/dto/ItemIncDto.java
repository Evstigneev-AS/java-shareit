package ru.practicum.shareit.item.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemIncDto {
    @NotNull
    String name;
    @NotNull
    String description;
    @NotNull
    Boolean available;
    Long requestId;
}
