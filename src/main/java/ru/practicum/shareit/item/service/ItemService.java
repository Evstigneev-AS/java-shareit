package ru.practicum.shareit.item.service;

import org.springframework.data.domain.Pageable;
import ru.practicum.shareit.item.dto.*;

import java.util.List;

public interface ItemService {
    ItemInfoResponseDto create(final long userId, final ItemCreateRequestDto itemCreateRequestDto);

    ItemInfoResponseDto update(final long userId, final long itemId, final ItemUpdateRequestDto itemUpdateRequestDto);

    ItemFullInfoResponseDto get(final long itemId);

    List<ItemInfoResponseDto> getAll(final long userId, final Pageable pageable);

    List<ItemInfoResponseDto> search(final String text, final Pageable pageable);

    CommentInfoResponseDto addComment(final long userId, final long itemId, final CommentAddRequestDto requestDto);
}