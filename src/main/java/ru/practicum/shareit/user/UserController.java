package ru.practicum.shareit.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.user.dto.UserCreateRequestDto;
import ru.practicum.shareit.user.dto.UserInfoResponseDto;
import ru.practicum.shareit.user.dto.UserUpdateRequestDto;
import ru.practicum.shareit.user.servise.UserService;

import java.util.List;

@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
@Slf4j
@Validated
public class UserController {
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public UserInfoResponseDto create(@RequestBody @Valid final UserCreateRequestDto userCreateRequestDto) {
        log.info("request POST /users body = {}", userCreateRequestDto);
        final UserInfoResponseDto result = userService.create(userCreateRequestDto);
        log.info("response POST /users body = {}", result);
        return result;
    }

    @PatchMapping("/{user-id}")
    @ResponseStatus(HttpStatus.OK)
    public UserInfoResponseDto update(@RequestBody @Valid final UserUpdateRequestDto userUpdateRequestDto,
                                      @PathVariable("user-id") final long userId) {
        log.info("request PATH /users/{} body = {}", userId, userUpdateRequestDto);
        final UserInfoResponseDto result = userService.update(userUpdateRequestDto, userId);
        log.info("response PATH /users/{} body = {}", userId, result);
        return result;
    }

    @GetMapping("/{user-id}")
    @ResponseStatus(HttpStatus.OK)
    public UserInfoResponseDto get(@PathVariable("user-id") final long userId) {
        log.info("request GET /users/{}", userId);
        final UserInfoResponseDto result = userService.get(userId);
        log.info("response GET /users/{} body = {}", userId, result);
        return result;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserInfoResponseDto> getAll() {
        log.info("request GET /users");
        final List<UserInfoResponseDto> result = userService.getAll();
        log.info("response GET /users body = {}", result);
        return result;
    }

    @DeleteMapping("/{user-id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("user-id") final long userId) {
        log.info("request DELETE /users/{}", userId);
        userService.delete(userId);
        log.info("response DELETE /users/{}", userId);
    }
}
