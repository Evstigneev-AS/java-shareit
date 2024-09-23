package ru.practicum.shareit.item.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.model.BookingStatus;
import ru.practicum.shareit.booking.storage.BookingRepository;
import ru.practicum.shareit.exception.ExceptionUtils;
import ru.practicum.shareit.exception.ValidationException;
import ru.practicum.shareit.item.dto.*;
import ru.practicum.shareit.item.model.Comment;
import ru.practicum.shareit.item.model.CommentMapper;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.model.ItemMapper;
import ru.practicum.shareit.item.storage.CommentRepository;
import ru.practicum.shareit.item.storage.ItemRepository;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.storage.UserRepository;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final BookingRepository bookingRepository;

    @Override
    public ItemInfoResponseDto create(final long userId, final ItemCreateRequestDto itemCreateRequestDto) {
        final User owner = getUserOrThrowError(userId);
        final Item newItem = ItemMapper.toModel(owner, itemCreateRequestDto);
        final Item saved = itemRepository.save(newItem);
        return ItemMapper.toDto(saved);
    }

    @Override
    public ItemInfoResponseDto update(final long userId, final long itemId,
                                      final ItemUpdateRequestDto itemUpdateRequestDto) {
        final User owner = getUserOrThrowError(userId);
        final Item original = getItemOrThrowError(itemId);
        final Item newItem = ItemMapper.toModel(owner, original, itemUpdateRequestDto);
        updateEntityIfCorrect(original, newItem);
        return ItemMapper.toDto(itemRepository.save(original));

    }

    private void updateEntityIfCorrect(final Item original, final Item newItem) {
        updateNameIfCorrect(original, newItem);
        updateDescriptionIfCorrect(original, newItem);
        updateAvailableIfCorrect(original, newItem);
    }

    private void updateNameIfCorrect(final Item original, final Item newItem) {
        if (!Objects.isNull(newItem.getName()) && !original.getName().equals(newItem.getName())) {
            original.setName(newItem.getName());
        }
    }

    private void updateDescriptionIfCorrect(final Item original, final Item newItem) {
        if (!Objects.isNull(newItem.getDescription()) && !original.getDescription().equals(newItem.getDescription())) {
            original.setDescription(newItem.getDescription());
        }
    }

    private void updateAvailableIfCorrect(final Item original, final Item newItem) {
        if (!Objects.isNull(newItem.getAvailable()) && !original.getAvailable() == newItem.getAvailable()) {
            original.setAvailable(newItem.getAvailable());
        }
    }

    @Override
    public ItemFullInfoResponseDto get(final long itemId) {
        final Item original = getItemOrThrowError(itemId);
        final List<Booking> bookings = bookingRepository.findByItemIdAndBookingStatus(itemId, BookingStatus.APPROVED);
        final LocalDateTime now = LocalDateTime.now();
        final Optional<Booking> lastBooking = bookings.stream()
                .filter(booking -> booking.getEnd().isAfter(now))
                .max(Comparator.comparing(Booking::getEnd));
        final Optional<Booking> nextBooking = bookings.stream()
                .filter(booking -> booking.getStart().isAfter(now))
                .min(Comparator.comparing(Booking::getStart));
        final List<Comment> comments = commentRepository.findAllByItemId(itemId);
        return ItemMapper.toFullDto(original, comments, lastBooking, nextBooking);
    }

    @Override
    public List<ItemInfoResponseDto> getAll(final long userId, final Pageable pageable) {
        getUserOrThrowError(userId);
        return itemRepository.findAllByOwnerId(userId, pageable).stream()
                .map(ItemMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ItemInfoResponseDto> search(final String text, final Pageable pageable) {
        if (Objects.isNull(text) || text.isBlank()) {
            return List.of();
        }
        return itemRepository.search(text, pageable).stream()
                .map(ItemMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CommentInfoResponseDto addComment(final long userId, final long itemId,
                                             final CommentAddRequestDto requestDto) {
        final User user = getUserOrThrowError(userId);
        final Item item = getItemOrThrowError(itemId);
        final List<Booking> bookings = bookingRepository.findByBookerIdAndItemIdAndBookingStatusAndEndBefore(userId,
                itemId, BookingStatus.APPROVED, LocalDateTime.now(), PageRequest.of(1 / 10, 1));
        if (bookings.isEmpty()) {
            throw new ValidationException(String.format("user = %s didnt book this item = %s", userId, itemId));
        }
        final Comment comment = CommentMapper.toModel(user, item, requestDto);
        comment.setCreated(LocalDateTime.now());
        return CommentMapper.toDto(commentRepository.save(comment));
    }

    private User getUserOrThrowError(final long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> ExceptionUtils.createNotFoundException(userId));
    }

    private Item getItemOrThrowError(final long itemId) {
        return itemRepository.findById(itemId).orElseThrow(
                () -> ExceptionUtils.createNotFoundException(itemId));
    }
}