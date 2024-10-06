package ru.practicum.shareit.item;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.annotations.ItemControllerExceptionHandler;
import ru.practicum.shareit.item.dto.model.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/items")
@ItemControllerExceptionHandler
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;
    private final String userIdHead = "X-Sharer-User-Id";

    @PostMapping
    public ItemOutDto createItem(@RequestBody final ItemIncDto itemDto,
                                 @RequestHeader(userIdHead) final Long userId) {

        log.info("Create Item; userId={} ", userId);
        return itemService.createItem(itemDto, userId);
    }

    @PatchMapping("/{item-id}")
    public ItemOutDto updateItem(@PathVariable("item-id") final Long itemId,
                                 @RequestBody final ItemIncDto itemDto,
                                 @RequestHeader(userIdHead) final Long userId) {

        log.info("Update Item; userId={}, itemId={} ", userId, itemId);
        return itemService.updateItem(itemId, itemDto, userId);
    }

    @GetMapping("/{item-id}")
    public ItemWidthBookingsTimeDto getItem(@RequestHeader(userIdHead) final Long userId,
                                            @PathVariable("item-id") final Long itemId) {

        log.info("Get Item; userId={}, itemId={} ", userId, itemId);
        return itemService.getItem(itemId, userId);
    }

    @GetMapping
    public List<ItemWidthBookingsTimeDto> getItemsUser(@RequestHeader(userIdHead) final Long userId,
                                                       @RequestParam final Integer from,
                                                       @RequestParam final Integer size) {

        log.info("Get Items user; userId={}, from={}, size={}", userId, from, size);
        return itemService.getItemsUser(userId, from, size);
    }

    @GetMapping("/search")
    public List<ItemOutDto> searchItems(@RequestParam final String text) {

        log.info("Search Items; text={}", text);
        return itemService.searchItems(text);
    }

    @PostMapping("/{item-id}/comment")
    public CommentOutDto addComment(@RequestHeader(userIdHead) final Long userId,
                                    @RequestBody final CommentIncDto comment,
                                    @PathVariable("item-id") final Long itemId) {

        log.info("Add Comment; userId={}, itemId={}", userId, itemId);
        return itemService.addComment(comment, itemId, userId);
    }
}
