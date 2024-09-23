package ru.practicum.shareit.request.model;

import lombok.Builder;
import lombok.Value;
import ru.practicum.shareit.user.model.User;

import java.time.LocalDateTime;

/**
 * TODO Sprint add-item-requests.
 */
@Value
@Builder
public class ItemRequest {
    Long id;
    String description;
    User requestor;
    LocalDateTime creationTime;
}
