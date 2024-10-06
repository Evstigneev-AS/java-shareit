package ru.practicum.shareit.request;

import ru.practicum.shareit.request.dto.model.RequestIncDto;
import ru.practicum.shareit.request.dto.model.RequestOutDto;
import ru.practicum.shareit.request.dto.model.RequestWithItemDto;

import java.util.List;

public interface RequestService {

    RequestOutDto createRequest(Long userId, RequestIncDto requestIncDto);

    List<RequestWithItemDto> getRequestsUser(final Long userId);

    List<RequestOutDto> getAllRequests(final Long userId,
                                       final Integer from,
                                       final Integer size);

    RequestWithItemDto getRequest(final Long requestId);
}
