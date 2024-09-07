package ru.practicum.shareit.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserDto {

    Long id;
    @NotBlank(message = "Имя пользователя не должно быть пустым")
    String name;
    @Email(message = "Электронная почта должна иметь формат адреса электронной почты")
    @NotBlank(message = "Электронная почта не должна быть пустой")
    String email;

    public UserDto withId(Long id) {
        if (id != null && id.equals(this.id)) {
            return this;
        }
        return new UserDto(id, this.name, this.email);
    }
}
