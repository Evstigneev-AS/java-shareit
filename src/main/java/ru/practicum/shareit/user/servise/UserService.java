package ru.practicum.shareit.user.servise;

import ru.practicum.shareit.user.dto.UserCreateRequestDto;
import ru.practicum.shareit.user.dto.UserInfoResponseDto;
import ru.practicum.shareit.user.dto.UserUpdateRequestDto;

import java.util.List;

public interface UserService {

    UserInfoResponseDto create(final UserCreateRequestDto userCreateRequestDto);

    UserInfoResponseDto update(final UserUpdateRequestDto userUpdateRequestDto, final long userId);

    UserInfoResponseDto get(final long userId);

    List<UserInfoResponseDto> getAll();

    void delete(final long userId);
}
