package com.substring.irctc.dto;

import com.substring.irctc.entity.BookingStatus;
import com.substring.irctc.entity.PaymentStatus;
 import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
public class BookingResponse {
    private Long bookingId;
    private LocalDate journeyDate;
    private LocalTime arrivalTime;
    private LocalTime departureTime;
    private StationDto sourceStation;
    private StationDto destinationStation;
    private String pnr;
    private BigDecimal totalFair;
    private BookingStatus bookingStatus;
    private PaymentStatus paymentStatus;
    private List<BookingPassengerDto> passengers;
}
