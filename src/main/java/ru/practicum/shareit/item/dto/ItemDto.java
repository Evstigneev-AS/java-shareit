package ru.practicum.shareit.item.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

/**
 * TODO Sprint add-controllers.
 */

@Value
@Builder
public class ItemDto {

    Long id;

    @NotBlank(message = "Имя предмета не должно быть пустым")
    String name;

    @NotBlank(message = "Описание предмета не должно быть пустым")
    String description;

    @NotNull(message = "Статус доступности предмета должен быть определен")
    Boolean available;

}
