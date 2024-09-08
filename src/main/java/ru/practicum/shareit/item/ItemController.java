package ru.practicum.shareit.item;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.service.ItemService;

import java.util.List;

/**
 * TODO Sprint add-controllers.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {

    public static final String X_SHARER_USER_ID = "X-Sharer-User-Id";
    private final ItemService itemService;

    @PostMapping
    public ResponseEntity<ItemDto> createItem(@Valid @RequestHeader(X_SHARER_USER_ID) Long userId,
                                              @Valid @RequestBody ItemDto itemDto) {
        ItemDto createdItem = itemService.createItem(userId, itemDto);
        log.info("Received POST request createItem with userId: {}", userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdItem);
    }

    @PatchMapping("/{item-id}")
    public ResponseEntity<ItemDto> patchItem(@Valid @RequestHeader(X_SHARER_USER_ID) Long userId,
                                             @Valid @PathVariable(name = "item-id") Long itemId,
                                             @RequestBody ItemDto itemDto) {
        ItemDto updatedItem = itemService.patchItem(userId, itemId, itemDto);
        log.info("Received PATCH request patchItem for itemId: {}", itemId);
        return ResponseEntity.ok(updatedItem);
    }

    @GetMapping("/{item-id}")
    public ResponseEntity<ItemDto> getItemById(@Valid @PathVariable(name = "item-id") Long itemId) {
        ItemDto item = itemService.getItemById(itemId);
        log.info("Received GET request getItemById with itemId: {}", itemId);
        return ResponseEntity.ok(item);
    }

    @GetMapping
    public ResponseEntity<List<ItemDto>> getAllItems(@Valid @RequestHeader(X_SHARER_USER_ID) Long userId) {
        List<ItemDto> itemDtoList = itemService.getAllItemsByUserId(userId);
        log.info("Received GET request getAllItems for userId: {}", userId);
        return ResponseEntity.ok(itemDtoList);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ItemDto>> searchItems(@RequestParam("text") String text) {
        List<ItemDto> itemDtoList = itemService.searchItems(text);
        log.info("Received GET request searchItem with text: {}", text);
        return ResponseEntity.ok(itemDtoList);
    }
}