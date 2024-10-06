package ru.practicum.shareit.request;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.shareit.request.model.Request;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long> {

    List<Request> findAllByRequestorIdOrderByCreatedDesc(Long requestorId);

    @Query("select r " +
            "from Request as r " +
            "where r.requestor.id <> :userId " +
            "order by r.created")
    Page<Request> getAllRequests(Long userId, Pageable paging);
}
