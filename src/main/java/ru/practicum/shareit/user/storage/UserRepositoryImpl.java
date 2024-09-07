package ru.practicum.shareit.user.storage;

import ru.practicum.shareit.user.model.User;

import java.util.HashMap;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private final HashMap<Long, User> storage = new HashMap<>();
    private static Long idCounter = 1L;

    public static Long generateId() {
        return idCounter = idCounter + 1;
    }

    @Override
    public User create(User user) {
        return null;
    }

    @Override
    public User patch(Long id, User user) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public User getUserById(Long id) {
        return null;
    }

    @Override
    public List<User> findAllUsers() {
        return List.of();
    }

    @Override
    public boolean contains(Long id) {
        return false;
    }
}
