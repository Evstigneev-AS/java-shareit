package ru.practicum.shareit.booking;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.booking.annotations.BookingControllerExceptionHandler;
import ru.practicum.shareit.booking.dto.model.BookingIncDto;
import ru.practicum.shareit.booking.dto.model.BookingOutDto;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(path = "/bookings")
@BookingControllerExceptionHandler
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;
    private final String userIdHead = "X-Sharer-User-Id";

    @PostMapping
    public BookingOutDto createBooking(@RequestBody final BookingIncDto bookingIncDto,
                                       @RequestHeader(userIdHead) final Long userId) {

        log.info("Post booking; userId={}, itemId={}", userId, bookingIncDto.getItemId());
        return bookingService.createBooking(bookingIncDto, userId);

    }

    @PatchMapping("/{booking-id}")
    public BookingOutDto approvedBooking(@RequestHeader(userIdHead) final Long userId,
                                         @PathVariable("booking-id") final Long bookingId,
                                         @RequestParam final Boolean approved) {

        log.info("Patch booking; userId={}, bookingId={}, approved={}", userId, bookingId, approved);
        return bookingService.approvedBooking(userId, bookingId, approved);
    }

    @GetMapping("/{booking-id}")
    public BookingOutDto getBooking(@RequestHeader(userIdHead) final Long userId,
                                    @PathVariable("booking-id") final Long bookingId) {

        log.info("Get booking; bookingId={}, userId={}", bookingId, userId);
        return bookingService.getBooking(userId, bookingId);
    }

    @GetMapping
    public List<BookingOutDto> getAllBookingsUser(@RequestHeader(userIdHead) final Long userId,
                                                  @RequestParam final String state,
                                                  @RequestParam final Integer from,
                                                  @RequestParam final Integer size) {

        log.info("Get bookings user; state={}, userId={}, from={}, size={}", state, userId, from, size);
        return bookingService.getAllBookingsUser(userId, state, from, size);
    }

    @GetMapping("/owner")
    public List<BookingOutDto> getAllBookingsItemsUser(@RequestHeader(userIdHead) final Long userId,
                                                       @RequestParam final String state,
                                                       @RequestParam final Integer from,
                                                       @RequestParam final Integer size) {

        log.info("Get bookings owner items user; state {}, userId={}, from={}, size={}", state, userId, from, size);
        return bookingService.getAllBookingsItemsUser(userId, state, from, size);
    }
}