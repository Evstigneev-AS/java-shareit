package ru.practicum.shareit.item;

import ru.practicum.shareit.item.dto.model.*;

import java.util.List;

public interface ItemService {

    ItemOutDto createItem(ItemIncDto itemDto, Long userId);

    ItemOutDto updateItem(Long itemId, ItemIncDto itemDto, Long userId);

    ItemWidthBookingsTimeDto getItem(Long itemId, Long userId);

    List<ItemWidthBookingsTimeDto> getItemsUser(Long userId,
                                                Integer from,
                                                Integer size);

    List<ItemOutDto> searchItems(String text);

    CommentOutDto addComment(CommentIncDto comment, Long itemId, Long userId);
}
