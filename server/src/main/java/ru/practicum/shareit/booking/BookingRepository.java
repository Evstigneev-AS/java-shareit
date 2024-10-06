package ru.practicum.shareit.booking;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.shareit.booking.enums.BookingStatusEnum;
import ru.practicum.shareit.booking.model.Booking;

import java.time.LocalDateTime;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("select b " +
            "from Booking as b " +
            "join User as u on b.booker.id = u.id " +
            "where u.id = :userId " +
            "order by b.start desc")
    Page<Booking> findAllByBooker(long userId, Pageable paging);

    @Query("select b " +
            "from Booking as b " +
            "join User as u on b.booker.id = u.id " +
            "where u.id = :userId " +
            "and b.start < :now " +
            "and b.end > :now " +
            "order by b.start")
    Page<Booking> findAllByBookerForStatusCurrent(long userId, LocalDateTime now, Pageable paging);

    @Query("select b " +
            "from Booking as b " +
            "join User as u on b.booker.id = u.id " +
            "where u.id = :userId " +
            "and b.end < :now " +
            "order by b.start desc")
    Page<Booking> findAllByBookerForStatusPast(long userId, LocalDateTime now, Pageable paging);

    @Query("select b " +
            "from Booking as b " +
            "join User as u on b.booker.id = u.id " +
            "where u.id = :userId " +
            "and b.start > :now " +
            "order by b.start desc")
    Page<Booking> findAllByBookerForStatusFuture(long userId, LocalDateTime now, Pageable paging);

    @Query("select b " +
            "from Booking as b " +
            "join User as u on b.booker.id = u.id " +
            "where u.id = :userId " +
            "and b.status = :status " +
            "order by b.start desc")
    Page<Booking> findAllByBookerForStatusWaitingOrRejected(long userId, BookingStatusEnum status, Pageable paging);

    @Query("select b " +
            "from Booking as b " +
            "join Item as it on b.item.id = it.id " +
            "join User as u on it.owner.id = u.id " +
            "where u.id = :userId " +
            "order by b.start desc")
    Page<Booking> findAllBookingsItemsUser(long userId, Pageable paging);

    @Query("select b " +
            "from Booking as b " +
            "join Item as it on b.item.id = it.id " +
            "join User as u on it.owner.id = u.id " +
            "where u.id = :userId " +
            "and b.start < :now " +
            "and b.end > :now " +
            "order by b.start desc")
    Page<Booking> findAllBookingsItemsUserForStatusCurrent(long userId, LocalDateTime now, Pageable paging);

    @Query("select b " +
            "from Booking as b " +
            "join Item as it on b.item.id = it.id " +
            "join User as u on it.owner.id = u.id " +
            "where u.id = :userId " +
            "and b.end < :now " +
            "order by b.start desc")
    Page<Booking> findAllBookingsItemsUserForStatusPast(long userId, LocalDateTime now, Pageable paging);

    @Query("select b " +
            "from Booking as b " +
            "join Item as it on b.item.id = it.id " +
            "join User as u on it.owner.id = u.id " +
            "where u.id = :userId " +
            "and b.start > :now " +
            "order by b.start desc")
    Page<Booking> findAllBookingsItemsUserForStatusFuture(long userId, LocalDateTime now, Pageable paging);

    @Query("select b " +
            "from Booking as b " +
            "join Item as it on b.item.id = it.id " +
            "join User as u on it.owner.id = u.id " +
            "where u.id = :userId " +
            "and b.status = :status " +
            "order by b.start desc")
    Page<Booking> findAllBookingsItemsUserForStatusWaitingOrRejected(long userId, BookingStatusEnum status, Pageable paging);

    @Query("select b " +
            "from Booking as b " +
            "where b.item.id = :itemId " +
            "and b.status = 'APPROVED' " +
            "and b.start < :now " +
            "order by b.end desc")
    Page<Booking> findLastBooking(Long itemId, LocalDateTime now, Pageable paging);

    @Query("select b " +
            "from Booking as b " +
            "where b.item.id = :itemId " +
            "and b.start > :now " +
            "and b.status <> 'REJECTED' " +
            "order by b.start ")
    Page<Booking> findNextBooking(Long itemId, LocalDateTime now, Pageable paging);

    @Query("select b " +
            "from Booking as b " +
            "where b.item.id = :itemId " +
            "and b.booker.id = :bookerId " +
            "and b.start < :now " +
            "and b.status = 'APPROVED' ")
    Page<Booking> searchForBookerIdAndItemId(Long bookerId, Long itemId, LocalDateTime now, Pageable paging);
}