package ru.practicum.shareit.user.model;

import lombok.experimental.UtilityClass;
import ru.practicum.shareit.user.dto.UserCreateRequestDto;
import ru.practicum.shareit.user.dto.UserInfoResponseDto;
import ru.practicum.shareit.user.dto.UserUpdateRequestDto;

@UtilityClass
public class UserMapper {

    public User toModel(final UserCreateRequestDto userCreateRequestDto) {
        return User.builder()
                .name(userCreateRequestDto.getName())
                .email(userCreateRequestDto.getEmail())
                .build();
    }


    public User toModel(UserUpdateRequestDto userUpdateRequestDto, long userId) {
        return User.builder()
                .id(userId)
                .name(userUpdateRequestDto.getName())
                .email(userUpdateRequestDto.getEmail())
                .build();
    }

    public UserInfoResponseDto toDto(User user) {
        return UserInfoResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}
