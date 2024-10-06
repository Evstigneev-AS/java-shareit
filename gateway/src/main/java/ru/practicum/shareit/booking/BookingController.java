package ru.practicum.shareit.booking;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.dto.BookingState;

@Controller
@RequestMapping(path = "/bookings")
@RequiredArgsConstructor
@Slf4j
public class BookingController {
    private final BookingClient bookingClient;
    private final String userIdHead = "X-Sharer-User-Id";

    @PostMapping
    public ResponseEntity<Object> createBooking(@RequestBody @Valid final BookingDto bookingDto,
                                                @RequestHeader(userIdHead) final Long userId) {

        log.info("Post booking; userId={}, itemId={}", userId, bookingDto.getItemId());
        return bookingClient.createBooking(userId, bookingDto);
    }

    @PatchMapping("/{booking-id}")
    public ResponseEntity<Object> approvedBooking(@RequestHeader(userIdHead) final Long userId,
                                                  @PathVariable("booking-id") final Long bookingId,
                                                  @RequestParam final Boolean approved) {

        log.info("Patch booking; userId={}, bookingId={}, approved={}", userId, bookingId, approved);
        return bookingClient.approvedBooking(userId, bookingId, approved);
    }

    @GetMapping("/{booking-id}")
    public ResponseEntity<Object> getBooking(@RequestHeader(userIdHead) final Long userId,
                                             @PathVariable("booking-id") final Long bookingId) {
        log.info("Get booking; bookingId={}, userId={}", bookingId, userId);
        return bookingClient.getBooking(userId, bookingId);
    }

    @GetMapping
    public ResponseEntity<Object> getAllBookingsUser(@RequestHeader(userIdHead) final Long userId,
                                                     @RequestParam(name = "state", defaultValue = "all") final String stateParam,
                                                     @PositiveOrZero @RequestParam(name = "from", defaultValue = "0") final Integer from,
                                                     @Positive @RequestParam(name = "size", defaultValue = "10") final Integer size) {

        BookingState state = BookingState.from(stateParam)
                .orElseThrow(() -> new IllegalArgumentException("Unknown state: " + stateParam));

        log.info("Get bookings user; state={}, userId={}, from={}, size={}", stateParam, userId, from, size);
        return bookingClient.getAllBookingsUser(userId, state, from, size);
    }

    @GetMapping("/owner")
    public ResponseEntity<Object> getAllBookingsItemsUser(@RequestHeader(userIdHead) final Long userId,
                                                          @RequestParam(name = "state", defaultValue = "all") final String stateParam,
                                                          @PositiveOrZero @RequestParam(name = "from", defaultValue = "0") final Integer from,
                                                          @Positive @RequestParam(name = "size", defaultValue = "10") final Integer size) {

        BookingState state = BookingState.from(stateParam)
                .orElseThrow(() -> new IllegalArgumentException("Unknown state: " + stateParam));

        log.info("Get bookings owner items user; state {}, userId={}, from={}, size={}", stateParam, userId, from, size);
        return bookingClient.getAllBookingsItemsUser(userId, state, from, size);
    }
}