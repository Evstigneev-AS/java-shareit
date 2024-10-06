package ru.practicum.shareit.user;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.user.dto.UserDto;

@Controller
@RequestMapping(path = "/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserClient client;

    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody @Valid final UserDto userDto) {
        log.info("Post user");
        return client.createUser(userDto);
    }

    @PatchMapping("/{user-id}")
    public ResponseEntity<Object> updateUser(@PathVariable("user-id") final Long userId,
                                             @RequestBody @Valid final UserDto userDto) {

        log.info("PATCH update user; userId={}", userId);
        return client.updateUser(userId, userDto);
    }

    @GetMapping("/{user-id}")
    public ResponseEntity<Object> getUser(@PathVariable("user-id") final Long userId) {

        log.info("GET user; userId={}", userId);
        return client.getUser(userId);
    }

    @DeleteMapping("/{user-id}")
    public ResponseEntity<Object> delUser(@PathVariable("user-id") final Long userId) {

        log.info("DELETE user; userId={}", userId);
        return client.delUser(userId);
    }

    @GetMapping
    public ResponseEntity<Object> getAllUsers(@PositiveOrZero @RequestParam(name = "from", defaultValue = "0") final Integer from,
                                              @Positive @RequestParam(name = "size", defaultValue = "10") final Integer size) {

        log.info("GET all users; from={}, size={}", from, size);
        return client.getAllUsers(from, size);
    }
}