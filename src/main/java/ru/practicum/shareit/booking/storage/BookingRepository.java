package ru.practicum.shareit.booking.storage;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.model.BookingStatus;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByBookerId(long userId, Pageable pageable);

    List<Booking> findByBookerIdAndBookingStatusOrderByStartDesc(long userId, BookingStatus status,
                                                                 Pageable pageable);

    List<Booking> findByBookerIdAndStartAfter(long userId, LocalDateTime localDateTime,
                                              Pageable pageable);

    List<Booking> findByBookerIdAndEndBefore(long userId, LocalDateTime localDateTime,
                                             Pageable pageable);

    List<Booking> findByBookerIdAndItemIdAndBookingStatusAndEndBefore(long userId, long itemId,
                                                                      BookingStatus bookingStatus,
                                                                      LocalDateTime localDateTime,
                                                                      Pageable pageable);

    List<Booking> findByBookerIdAndStartBetween(long userId,
                                                LocalDateTime start,
                                                LocalDateTime end,
                                                Pageable pageable);

    List<Booking> findByItemOwnerId(long ownerId, Pageable pageable);

    List<Booking> findByItemOwnerIdAndStartBetween(long ownerId,
                                                   LocalDateTime start,
                                                   LocalDateTime end,
                                                   Pageable pageable);

    List<Booking> findByItemOwnerIdAndEndBefore(long ownerId,
                                                LocalDateTime localDateTime,
                                                Pageable pageable);

    List<Booking> findByItemOwnerIdAndStartAfter(long ownerId,
                                                 LocalDateTime localDateTime,
                                                 Pageable pageable);

    List<Booking> findByItemOwnerIdAndBookingStatusOrderByStartDesc(long ownerId, BookingStatus status,
                                                                    Pageable pageable);

    List<Booking> findByItemIdAndBookingStatus(long itemId, BookingStatus status);
}
