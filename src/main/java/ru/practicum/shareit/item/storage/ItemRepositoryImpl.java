package ru.practicum.shareit.item.storage;

import org.springframework.stereotype.Repository;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.validation.exeption.NotOwnerException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

@Repository
public class ItemRepositoryImpl implements ItemRepository {

    private final HashMap<Long, Item> storage = new HashMap<>();
    private static Long idCounter = 1L;

    public static Long generateId() {
        return idCounter++;
    }

    @Override
    public Item create(Long userId, Item item) {
        Long itemId = generateId();
        Item itemWithId = Item.builder()
                .id(itemId)
                .name(item.getName())
                .description(item.getDescription())
                .available(item.getAvailable())
                .ownerId(userId)
                .build();
        storage.put(itemId, itemWithId);
        return itemWithId;
    }

    @Override
    public Item patch(Long userId, Long itemId, Item item) {
        Item existingItem = storage.get(itemId);
        if (!existingItem.getOwnerId().equals(userId)) {
            throw new NotOwnerException("Данный предмет принадлежит другому пользователю");
        }
        String name;
        if (item.getName() != null) {
            name = item.getName();
        } else {
            name = existingItem.getName();
        }
        String description;
        if (item.getDescription() != null) {
            description = item.getDescription();
        } else {
            description = existingItem.getDescription();
        }
        Boolean available;
        if (item.getAvailable() != null) {
            available = item.getAvailable();
        } else {
            available = existingItem.getAvailable();
        }

        Item updatedItem = Item.builder()
                .id(itemId)
                .name(name)
                .description(description)
                .available(available)
                .ownerId(existingItem.getOwnerId())
                .build();

        storage.put(itemId, updatedItem);
        return updatedItem;
    }

    @Override
    public Item getById(Long itemId) {
        return storage.get(itemId);
    }

    @Override
    public List<Item> getAllByUserId(Long userId) {
        List<Item> list = new ArrayList<>();
        for (Item item : storage.values()) {
            if (userId.equals(item.getOwnerId())) {
                list.add(item);
            }
        }
        return list;
    }

    @Override
    public List<Item> search(String text) {
        if (text.isBlank() || text.isEmpty()) {
            return List.of();
        }
        String searchText = text.toLowerCase(Locale.ROOT);
        List<Item> list = new ArrayList<>();
        for (Item item : storage.values()) {
            if (containsStringIgnoreCase(item.getName(), searchText) || containsStringIgnoreCase(item.getDescription(), searchText)) {
                if (item.getAvailable()) {
                    list.add(item);
                }
            }
        }
        return list;
    }

    @Override
    public boolean contains(Long id) {
        return storage.containsKey(id);
    }

    private boolean containsStringIgnoreCase(String source, String target) {
        return source.toLowerCase(Locale.ROOT).contains(target);
    }
}
