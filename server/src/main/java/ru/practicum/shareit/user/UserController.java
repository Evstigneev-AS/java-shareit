package ru.practicum.shareit.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.user.annotations.UserControllerExceptionHandler;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.model.ResponseToUserDeletion;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/users")
@UserControllerExceptionHandler
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserDto createUser(@RequestBody final UserDto userDto) {
        log.info("Create user");
        return userService.createUser(userDto);
    }

    @PatchMapping("/{user-id}")
    public UserDto updateUser(@PathVariable("user-id") final Long userId,
                              @RequestBody final UserDto userDto) {

        log.info("PATCH update user; userId={}", userId);
        return userService.updateUser(userId, userDto);
    }

    @GetMapping("/{user-id}")
    public UserDto getUser(@PathVariable("user-id") final Long userId) {

        log.info("Get user; userId={}", userId);
        return userService.getUser(userId);

    }

    @DeleteMapping("/{user-id}")
    public ResponseToUserDeletion delUser(@PathVariable("user-id") final Long userId) {

        log.info("Delete user; userId={}", userId);
        return userService.delUser(userId);
    }

    @GetMapping
    public List<UserDto> getAllUsers(@RequestParam final Integer from,
                                     @RequestParam final Integer size) {

        log.info("Get all users; from={}, size={}", from, size);
        return userService.getAllUsers(from, size);
    }
}
