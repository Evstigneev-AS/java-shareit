package ru.practicum.shareit.item.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.model.ItemMapper;
import ru.practicum.shareit.item.storage.ItemRepository;
import ru.practicum.shareit.user.storage.UserRepository;
import ru.practicum.shareit.validation.exeption.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    @Override
    public ItemDto createItem(Long userId, ItemDto itemDto) {
        if (!userRepository.contains(userId)) {
            throw new NotFoundException("User not found");
        }
        Item item = ItemMapper.toItem(itemDto);
        Item createdItem = itemRepository.create(userId, item);
        return ItemMapper.toItemDto(createdItem);
    }

    @Override
    public ItemDto patchItem(Long userId, Long itemId, ItemDto itemDto) {
        if (!itemRepository.contains(itemId)) {
            throw new NotFoundException("Item not found");
        }
        if (!userRepository.contains(userId)) {
            throw new NotFoundException("User not found");
        }
        Item item = ItemMapper.toItem(itemDto);
        Item updatedItem = itemRepository.patch(userId, itemId, item);
        return ItemMapper.toItemDto(updatedItem);
    }

    @Override
    public ItemDto getItemById(Long itemId) {
        Item item = itemRepository.getById(itemId);
        return ItemMapper.toItemDto(item);
    }

    @Override
    public List<ItemDto> getAllItemsByUserId(Long userId) {
        List<Item> items = itemRepository.getAllByUserId(userId);
        List<ItemDto> list = new ArrayList<>();
        for (Item item : items) {
            ItemDto itemDto = ItemMapper.toItemDto(item);
            list.add(itemDto);
        }
        return list;
    }

    @Override
    public List<ItemDto> searchItems(String text) {
        List<Item> items = itemRepository.search(text);
        List<ItemDto> list = new ArrayList<>();
        for (Item item : items) {
            ItemDto itemDto = ItemMapper.toItemDto(item);
            list.add(itemDto);
        }
        return list;
    }
}
