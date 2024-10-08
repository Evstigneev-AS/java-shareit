package ru.practicum.shareit.item.storage;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.shareit.item.model.Item;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    @Query("select i " +
           "from Item as i " +
           "where i.available = true and " +
           "(lower(i.name) like lower(concat('%', ?1, '%') ) or " +
           "lower(i.description) like lower(concat('%', ?1, '%') ))")
    List<Item> search(final String text, final Pageable pageable);

    List<Item> findAllByOwnerId(final long ownerId, final Pageable pageable);
}
