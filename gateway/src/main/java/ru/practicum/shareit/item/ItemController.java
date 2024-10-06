package ru.practicum.shareit.item;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.dto.CommentDto;
import ru.practicum.shareit.item.dto.ItemIncDto;

@Controller
@RequestMapping(path = "/items")
@RequiredArgsConstructor
@Slf4j
public class ItemController {
    private final ItemClient client;
    private final String userIdHead = "X-Sharer-User-Id";

    @PostMapping
    public ResponseEntity<Object> createItem(@RequestBody @Valid final ItemIncDto itemDto,
                                             @RequestHeader(userIdHead) final Long userId) {

        log.info("POST create Item; userId={} ", userId);
        return client.createItem(itemDto, userId);
    }

    @PatchMapping("/{item-id}")
    public ResponseEntity<Object> updateItem(@PathVariable("item-id") final Long itemId,
                                             @RequestBody final ItemIncDto itemDto,
                                             @RequestHeader(userIdHead) final Long userId) {

        log.info("PATCH update Item; userId={}, itemId={} ", userId, itemId);
        return client.updateItem(itemId, itemDto, userId);
    }

    @GetMapping("/{item-id}")
    public ResponseEntity<Object> getItem(@RequestHeader(userIdHead) final Long userId,
                                          @PathVariable("item-id") final Long itemId) {

        log.info("GET Item; userId={}, itemId={} ", userId, itemId);
        return client.getItem(userId, itemId);
    }

    @GetMapping
    public ResponseEntity<Object> getItemsUser(@RequestHeader(userIdHead) final Long userId,
                                               @PositiveOrZero @RequestParam(name = "from", defaultValue = "0") final Integer from,
                                               @Positive @RequestParam(name = "size", defaultValue = "10") final Integer size) {

        log.info("GET Items user; userId={}, from={}, size={}", userId, from, size);
        return client.getItemsUser(userId, from, size);
    }

    @GetMapping("/search")
    public ResponseEntity<Object> searchItems(@RequestParam final String text) {

        log.info("GET search Items; text={}", text);
        return client.searchItems(text);
    }

    @PostMapping("/{item-id}/comment")
    public ResponseEntity<Object> addComment(@RequestHeader(userIdHead) final Long userId,
                                             @RequestBody @Valid final CommentDto comment,
                                             @PathVariable("item-id") final Long itemId) {

        log.info("POST add Comment; userId={}, itemId={}", userId, itemId);
        return client.addComment(userId, comment, itemId);
    }
}