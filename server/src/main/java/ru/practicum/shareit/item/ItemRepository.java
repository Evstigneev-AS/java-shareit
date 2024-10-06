package ru.practicum.shareit.item;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.shareit.item.model.Item;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    Page<Item> findAllByOwnerId(Long ownerId, Pageable paging);

    @Query("select it " +
            "from Item as it " +
            "where it.available = true " +
            "and (lower(it.name) like :text " +
            "or lower(it.description) like :text)")
    List<Item> searchByNameOrDescription(String text);

    List<Item> findAllByRequestId(Long requestId);
}