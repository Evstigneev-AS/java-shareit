package ru.practicum.shareit.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.servise.UserService;

import java.util.List;

/**
 * TODO Sprint add-controllers.
 */
@RestController
@RequestMapping(path = "/users")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        UserDto createdUser = userService.createUser(userDto);
        log.info("Received POST request createUser");
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PatchMapping("/{user-id}")
    public ResponseEntity<UserDto> patchUser(@PathVariable(name = "user-id") Long userId, @RequestBody UserDto userDto) {
        UserDto patchUser = userService.patchUser(userId, userDto);
        log.info("Received PATCH request patchUser with userId: {}", userId);
        return new ResponseEntity<>(patchUser, HttpStatus.OK);
    }

    @GetMapping("/{user-id}")
    public ResponseEntity<UserDto> getUserById(@Valid @PathVariable(name = "user-id") Long userId) {
        UserDto user = userService.getUserById(userId);
        log.info("Received GET request getUserById with userId: {}", userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> userDtoList = userService.getAllUsers();
        log.info("Received GET request getAllUsers.");
        return new ResponseEntity<>(userDtoList, HttpStatus.OK);
    }

    @DeleteMapping("/{user-id}")
    public ResponseEntity<Void> deleteUser(@Valid @PathVariable(name = "user-id") Long userId) {
        userService.deleteUser(userId);
        log.info("Received DELETE request to delete user with userId: {}", userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
