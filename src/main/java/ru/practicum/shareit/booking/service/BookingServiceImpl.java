package ru.practicum.shareit.booking.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.booking.dto.BookingCreateRequestDto;
import ru.practicum.shareit.booking.dto.BookingInfoResponseDto;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.model.BookingMapper;
import ru.practicum.shareit.booking.model.BookingState;
import ru.practicum.shareit.booking.model.BookingStatus;
import ru.practicum.shareit.booking.storage.BookingRepository;
import ru.practicum.shareit.exception.ExceptionUtils;
import ru.practicum.shareit.exception.NotAvailableForBookingException;
import ru.practicum.shareit.exception.ValidationException;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.storage.ItemRepository;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.storage.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    @Override
    public BookingInfoResponseDto create(final long userId, final BookingCreateRequestDto bookingCreateRequestDto) {
        final User booker = getUserOrThrowException(userId);
        final Item item = getItemOrThrowException(bookingCreateRequestDto.getItemId());
        final Booking booking = BookingMapper.toModel(booker, item, bookingCreateRequestDto);
        booking.setBookingStatus(BookingStatus.WAITING);
        return BookingMapper.toDto(bookingRepository.save(booking));
    }

    @Override
    public BookingInfoResponseDto approve(final long userId, final long bookingId, final boolean isApproved) {
        final Booking original = getBookingOrThrowException(bookingId);

        final User booker = userRepository.findById(userId).orElseThrow(
                () -> new NotAvailableForBookingException(String.format("User = %s isn't owner for itemId = %s",
                        userId, original.getItem().getId())));
        checkOwner(booker, original);
        original.setBookingStatus(isApproved ? BookingStatus.APPROVED : BookingStatus.REJECTED);
        return BookingMapper.toDto(bookingRepository.save(original));
    }

    @Override
    public BookingInfoResponseDto getByBookingId(final long userId, final long bookingId) {
        final User user = getUserOrThrowException(userId);
        final Booking booking = getBookingOrThrowException(bookingId);
        checkUser(user, booking);
        return BookingMapper.toDto(booking);
    }

    @Override
    public List<BookingInfoResponseDto> getBookings(final long userId, final String bookingState,
                                                    final Pageable pageable) {
        final BookingState state = getStateOrThrowException(bookingState);
        getUserOrThrowException(userId);
        switch (state) {
            case CURRENT -> {
                return BookingMapper.toDto(
                        bookingRepository.findByBookerIdAndStartBetween(userId,
                                LocalDateTime.now(), LocalDateTime.now(), pageable));
            }
            case PAST -> {
                return BookingMapper.toDto(bookingRepository.findByBookerIdAndEndBefore(userId,
                        LocalDateTime.now(), pageable));
            }
            case FUTURE -> {
                return BookingMapper.toDto(bookingRepository.findByBookerIdAndStartAfter(userId,
                        LocalDateTime.now(), pageable));
            }
            case WAITING -> {
                return BookingMapper.toDto(bookingRepository.findByBookerIdAndBookingStatusOrderByStartDesc(userId,
                        BookingStatus.WAITING, pageable));
            }
            case REJECTED -> {
                return BookingMapper.toDto(bookingRepository.findByBookerIdAndBookingStatusOrderByStartDesc(userId,
                        BookingStatus.REJECTED, pageable));
            }
        }
        return BookingMapper.toDto(bookingRepository.findByBookerId(userId, pageable));
    }

    @Override
    public List<BookingInfoResponseDto> getBookingsByOwner(long ownerId, String bookingState, Pageable pageable) {
        final BookingState state = getStateOrThrowException(bookingState);
        getUserOrThrowException(ownerId);
        switch (state) {
            case CURRENT -> {
                return BookingMapper.toDto(
                        bookingRepository.findByItemOwnerIdAndStartBetween(ownerId,
                                LocalDateTime.now(), LocalDateTime.now(), pageable));
            }
            case PAST -> {
                return BookingMapper.toDto(
                        bookingRepository.findByItemOwnerIdAndEndBefore(ownerId, LocalDateTime.now(),
                                pageable));
            }
            case FUTURE -> {
                return BookingMapper.toDto(
                        bookingRepository.findByItemOwnerIdAndStartAfter(ownerId,
                                LocalDateTime.now(), pageable));
            }
            case WAITING -> {
                return BookingMapper.toDto(
                        bookingRepository.findByItemOwnerIdAndBookingStatusOrderByStartDesc(ownerId,
                                BookingStatus.WAITING, pageable));
            }
            case REJECTED -> {
                return BookingMapper.toDto(
                        bookingRepository.findByItemOwnerIdAndBookingStatusOrderByStartDesc(ownerId,
                                BookingStatus.REJECTED, pageable));
            }
        }
        return BookingMapper.toDto(bookingRepository.findByItemOwnerId(ownerId, pageable));
    }

    private User getUserOrThrowException(final long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> ExceptionUtils.createNotFoundException(userId));
    }

    private void checkOwner(final User user, final Booking booking) {
        if (!isOwner(user, booking)) {
            throw new ValidationException(String.format("User = %s isn't owner for itemId = %s",
                    user.getId(), booking.getItem().getId()));
        }
    }

    private boolean isOwner(final User user, final Booking booking) {
        return booking.getItem().getOwner().equals(user);
    }

    private boolean isBooker(final User user, final Booking booking) {
        return booking.getBooker().equals(user);
    }

    private void checkUser(final User user, final Booking booking) {
        if (!isOwner(user, booking) && !isBooker(user, booking)) {
            throw new ValidationException(String.format("User = %s don't have access for itemId = %s",
                    user.getId(), booking.getItem().getId()));
        }
    }

    private Item getItemOrThrowException(final long itemId) {
        final Item original = itemRepository.findById(itemId)
                .orElseThrow(
                        () -> ExceptionUtils.createNotFoundException(itemId));
        return Optional.of(original)
                .filter(Item::getAvailable)
                .orElseThrow(() -> new NotAvailableForBookingException("Item isn't available for booking"));
    }

    private Booking getBookingOrThrowException(final long bookingId) {
        return bookingRepository.findById(bookingId).orElseThrow(
                () -> ExceptionUtils.createNotFoundException(bookingId));
    }

    private BookingState getStateOrThrowException(final String bookingState) {
        return Optional.of(BookingState.valueOf(bookingState)).orElseThrow(
                () -> new IllegalArgumentException(String.format("Unknown state %s", bookingState))
        );
    }
}