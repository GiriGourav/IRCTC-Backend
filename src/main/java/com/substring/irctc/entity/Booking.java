package com.substring.irctc.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String user;

    private TrainSchedule trainSchedule;

    private Station sourceStation;

    private Station destinationStation;

    private LocalDate journeyDate;

    private BigDecimal totalFair;

    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;

    private LocalDateTime createdAt;

    @OneToMany(mappedBy ="booking")
    private List<BookingPassenger> passengers;

    private Payment payment;

}
