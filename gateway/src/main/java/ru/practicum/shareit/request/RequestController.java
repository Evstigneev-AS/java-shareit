package ru.practicum.shareit.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.request.dto.RequestDto;

@Controller
@RequestMapping(path = "/requests")
@RequiredArgsConstructor
@Slf4j
public class RequestController {
    private final RequestClient client;
    private final String userIdHead = "X-Sharer-User-Id";

    @PostMapping
    public ResponseEntity<Object> createRequest(@RequestHeader(userIdHead) final Long userId,
                                                @RequestBody @Valid final RequestDto requestDto) {

        log.info("Create request; userId={}", userId);
        return client.createRequest(userId, requestDto);
    }

    @GetMapping
    public ResponseEntity<Object> getRequestsUser(@RequestHeader(userIdHead) final Long userId) {

        log.info("Get user requests; userId={}", userId);
        return client.getRequestsUser(userId);
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAllRequests(@RequestHeader(userIdHead) final Long userId,
                                                 @PositiveOrZero @RequestParam(name = "from", defaultValue = "0") final Integer from,
                                                 @Positive @RequestParam(name = "size", defaultValue = "10") final Integer size) {

        log.info("Get all requests; userId={}, from={}, size={}", userId, from, size);
        return client.getAllRequests(userId, from, size);
    }

    @GetMapping("/{request-id}")
    public ResponseEntity<Object> getRequest(@PathVariable("request-id") final Long requestId) {

        log.info("Get request; requestId={}", requestId);
        return client.getRequest(requestId);
    }
}
