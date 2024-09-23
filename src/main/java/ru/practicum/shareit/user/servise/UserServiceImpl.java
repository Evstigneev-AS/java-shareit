package ru.practicum.shareit.user.servise;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.ExceptionUtils;
import ru.practicum.shareit.exception.NotUniqueEmail;
import ru.practicum.shareit.user.dto.UserCreateRequestDto;
import ru.practicum.shareit.user.dto.UserInfoResponseDto;
import ru.practicum.shareit.user.dto.UserUpdateRequestDto;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.model.UserMapper;
import ru.practicum.shareit.user.storage.UserRepository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserInfoResponseDto create(final UserCreateRequestDto userCreateRequestDto) {
        try {
            final User newUser = UserMapper.toModel(userCreateRequestDto);
            return UserMapper.toDto(userRepository.save(newUser));
        } catch (DataIntegrityViolationException e) {
            throw new NotUniqueEmail(userCreateRequestDto.getEmail());
        }
    }

    @Override
    public UserInfoResponseDto update(final UserUpdateRequestDto userUpdateRequestDto, final long userId) {
        try {
            final User original = userRepository.findById(userId)
                    .orElseThrow(() -> ExceptionUtils.createNotFoundException(userId));
            final User newUser = UserMapper.toModel(userUpdateRequestDto, userId);
            updateEntityIfCorrect(original, newUser);
            return UserMapper.toDto(userRepository.save(original));
        } catch (DataIntegrityViolationException e) {
            throw new NotUniqueEmail(userUpdateRequestDto.getEmail());
        }
    }

    private void updateEntityIfCorrect(final User original, final User newUser) {
        updateEmailIfCorrect(original, newUser);
        updateNameIfCorrect(original, newUser);
    }

    private void updateEmailIfCorrect(final User original, final User newUser) {
        if (!Objects.isNull(newUser.getEmail()) && !newUser.getEmail().equals(original.getEmail())) {
            original.setEmail(newUser.getEmail());
        }
    }

    private void updateNameIfCorrect(final User original, final User newUser) {
        if (!Objects.isNull(newUser.getName()) && !newUser.getName().equals(original.getName())) {
            original.setName(newUser.getName());
        }
    }

    @Override
    public UserInfoResponseDto get(final long userId) {
        return UserMapper.toDto(userRepository.findById(userId)
                .orElseThrow(() -> ExceptionUtils.createNotFoundException(userId)));
    }

    @Override
    public List<UserInfoResponseDto> getAll() {
        return userRepository.findAll().stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(final long userId) {
        userRepository.findById(userId).orElseThrow(
                () -> ExceptionUtils.createNotFoundException(userId)
        );
        userRepository.deleteById(userId);
    }
}
