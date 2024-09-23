package ru.practicum.shareit.item.model;

import lombok.experimental.UtilityClass;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.model.BookingMapper;
import ru.practicum.shareit.item.dto.ItemCreateRequestDto;
import ru.practicum.shareit.item.dto.ItemFullInfoResponseDto;
import ru.practicum.shareit.item.dto.ItemInfoResponseDto;
import ru.practicum.shareit.item.dto.ItemUpdateRequestDto;
import ru.practicum.shareit.user.model.User;

import java.util.List;
import java.util.Optional;

@UtilityClass
public class ItemMapper {

    public Item toModel(final User user, final ItemCreateRequestDto itemCreateRequestDto) {
        return Item.builder()
                .name(itemCreateRequestDto.getName())
                .description(itemCreateRequestDto.getDescription())
                .available(itemCreateRequestDto.getAvailable())
                .owner(user)
                .build();
    }


    public Item toModel(final User user, final Item item, ItemUpdateRequestDto itemUpdateRequestDto) {
        return Item.builder()
                .id(item.getId())
                .name(itemUpdateRequestDto.getName())
                .description(itemUpdateRequestDto.getDescription())
                .available(itemUpdateRequestDto.getAvailable())
                .owner(user)
                .build();
    }


    public ItemInfoResponseDto toDto(final Item item) {
        return ItemInfoResponseDto.builder()
                .id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .available(item.getAvailable())
                .build();
    }

    public ItemFullInfoResponseDto toFullDto(final Item item, final List<Comment> comments,
                                             final Optional<Booking> lastBooking,
                                             final Optional<Booking> nextBooking) {
        ItemFullInfoResponseDto.ItemFullInfoResponseDtoBuilder builder = ItemFullInfoResponseDto.builder()
                .id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .available(item.getAvailable())
                .comments(CommentMapper.toDto(comments));
        lastBooking.ifPresent(booking -> builder.lastBooking(BookingMapper.toDto(booking)));
        nextBooking.ifPresent(booking -> builder.nextBooking(BookingMapper.toDto(booking)));

        return builder.build();
    }
}
