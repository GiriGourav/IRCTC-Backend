package com.substring.irctc.service;

import com.substring.irctc.dto.BookingRequest;
import com.substring.irctc.dto.BookingResponse;
import org.springframework.stereotype.Service;

@Service
public interface BookingService {
    BookingResponse createBooking(BookingRequest bookingRequest);
}
