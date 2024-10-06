package ru.practicum.shareit.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.shareit.item.model.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("select c " +
            "from Comment as c " +
            "join Item as it on c.item.id = it.id " +
            "and c.item.id = :itemId " +
            "order by c.id")
    List<Comment> findAllByItem(Long itemId);
}
