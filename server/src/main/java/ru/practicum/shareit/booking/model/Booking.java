package ru.practicum.shareit.booking.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.shareit.booking.converter.BookingStatusConverter;
import ru.practicum.shareit.booking.enums.BookingStatusEnum;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.model.User;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "Bookings")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "start_time")
    LocalDateTime start;

    @Column(name = "end_time")
    LocalDateTime end;

    @ManyToOne
    @JoinColumn(name = "item", nullable = false)
    Item item;

    @ManyToOne
    @JoinColumn(name = "booker", nullable = false)
    User booker;

    @Column(name = "status")
    @Convert(converter = BookingStatusConverter.class)
    BookingStatusEnum status;
}