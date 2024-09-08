package ru.practicum.shareit.user.servise;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.model.UserMapper;
import ru.practicum.shareit.user.storage.UserRepository;
import ru.practicum.shareit.validation.exeption.NotFoundException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = UserMapper.toUser(userDto);
        User createdUser = userRepository.create(user);
        return UserMapper.toUserDto(createdUser);
    }

    @Override
    public UserDto patchUser(Long userId, UserDto userDto) {
        if (!userRepository.contains(userId)) {
            throw new NotFoundException("User not found");
        }
        User user = UserMapper.toUser(userDto);
        User patchedUser = userRepository.patch(userId, user);
        return UserMapper.toUserDto(patchedUser);
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user = userRepository.getUserById(userId);
        return UserMapper.toUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAllUsers();
        List<UserDto> list = new ArrayList<>();
        for (User user : users) {
            UserDto userDto = UserMapper.toUserDto(user);
            list.add(userDto);
        }
        return Collections.unmodifiableList(list);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.delete(userId);
    }
}
