package ru.practicum.shareit.booking.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingCreateRequestDto {
    @NotNull
    Long itemId;
    @NotNull
    LocalDateTime start;
    @NotNull
    LocalDateTime end;

    @AssertTrue(message = "The start time must be in the future.")
    private boolean isStartInFuture() {
        return start != null && LocalDateTime.now().isBefore(start);
    }

    @AssertTrue(message = "The end time must be in the future.")
    private boolean isEndInFuture() {
        return end != null && LocalDateTime.now().isBefore(end);
    }

    @AssertTrue(message = "The end time must be after the start time.")
    private boolean isStartBeforeEnd() {
        return start != null && end != null && start.isBefore(end);
    }

    @AssertTrue(message = "The start time and end time cannot be the same.")
    private boolean isStartNotEqualToEnd() {
        return start != null && end != null && !start.isEqual(end);
    }
}
