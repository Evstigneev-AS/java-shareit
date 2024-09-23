package ru.practicum.shareit.item.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;
import ru.practicum.shareit.request.model.ItemRequest;

/**
 * TODO Sprint add-controllers.
 */
@Value
@Builder
public class Item {

    Long id;

    @NotBlank(message = "Имя предмета не должно быть пустым")
    String name;

    @NotBlank(message = "Описание предмета не должно быть пустым")
    String description;

    @NotNull(message = "Статус доступности предмета должен быть определен")
    Boolean available;

    Long ownerId;

    ItemRequest request;

}
