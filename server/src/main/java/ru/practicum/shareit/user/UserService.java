package ru.practicum.shareit.user;

import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.model.ResponseToUserDeletion;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto updateUser(Long userId, UserDto userDto);

    UserDto getUser(Long userId);

    ResponseToUserDeletion delUser(Long userId);

    List<UserDto> getAllUsers(Integer from, Integer size);
}
