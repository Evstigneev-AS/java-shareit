package ru.practicum.shareit.item;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.dto.*;
import ru.practicum.shareit.item.service.ItemService;

import java.util.List;

@RestController
@Validated
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {

    public static final String X_SHARER_USER_ID = "X-Sharer-User-Id";
    private final ItemService itemService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ItemInfoResponseDto createItem(@RequestHeader(X_SHARER_USER_ID) final long userid,
                                          @RequestBody @Valid final ItemCreateRequestDto itemCreateRequestDto) {
        log.info("request POST /items userId = {}, body = {}", userid, itemCreateRequestDto);
        final ItemInfoResponseDto result = itemService.create(userid, itemCreateRequestDto);
        log.info("response POST /items userId = {}, body = {}", userid, result);
        return result;
    }

    @PatchMapping("/{item-id}")
    @ResponseStatus(HttpStatus.OK)
    public ItemInfoResponseDto updateItem(@RequestHeader(X_SHARER_USER_ID) final long userId,
                                          @PathVariable("item-id") final long itemId,
                                          @RequestBody @Valid final ItemUpdateRequestDto itemUpdateRequestDto) {
        log.info("request PATCH /items/{} userId = {}, body = {}", itemId, userId, itemUpdateRequestDto);
        final ItemInfoResponseDto result = itemService.update(userId, itemId, itemUpdateRequestDto);
        log.info("response PATCH /items{} userId = {},body = {}", itemId, userId, result);
        return result;
    }

    @GetMapping("/{item-id}")
    @ResponseStatus(HttpStatus.OK)
    public ItemFullInfoResponseDto get(@PathVariable("item-id") final long itemId) {
        log.info("request GET /items/{} ", itemId);
        final ItemFullInfoResponseDto result = itemService.get(itemId);
        log.info("response GET /items/ body ={}", result);
        return result;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ItemInfoResponseDto> getAll(@RequestHeader(X_SHARER_USER_ID) final long userid,
                                            @RequestParam(value = "from", defaultValue = "0") final int from,
                                            @RequestParam(value = "size", defaultValue = "10") final int size) {
        log.info("request GET /items/ userId = {}", userid);
        final Pageable pageable = PageRequest.of(from / size, size);
        final List<ItemInfoResponseDto> result = itemService.getAll(userid, pageable);
        log.info("response GET /items/ userId= {}, body = {}", userid, result);
        return result;
    }

    @GetMapping("/search")
    public List<ItemInfoResponseDto> search(@RequestParam(name = "text") final String text,
                                            @RequestParam(value = "from", defaultValue = "0") final int from,
                                            @RequestParam(value = "size", defaultValue = "10") final int size) {
        log.info("request GET /items/search?text={}", text);
        final Pageable pageable = PageRequest.of(from / size, size);
        List<ItemInfoResponseDto> result = itemService.search(text, pageable);
        log.info("response GET /items/search?text={}, body = {}", text, result);
        return result;
    }

    @PostMapping("/{item-id}/comment")
    @ResponseStatus(HttpStatus.OK)
    public CommentInfoResponseDto addComment(@RequestHeader(X_SHARER_USER_ID) final long userId,
                                             @PathVariable("item-id") final long itemId,
                                             @RequestBody @Valid final CommentAddRequestDto commentAddRequestDto) {
        log.info("request POST /items/{}/comment userId ={}, body = {}", itemId, userId, commentAddRequestDto);
        final CommentInfoResponseDto result = itemService.addComment(userId, itemId, commentAddRequestDto);
        log.info("response POST /items/{}/comment userId = {}, body = {}", userId, itemId, result);
        return result;
    }
}