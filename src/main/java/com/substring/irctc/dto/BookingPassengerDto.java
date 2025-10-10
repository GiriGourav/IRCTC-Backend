package com.substring.irctc.dto;

import lombok.*;

@Getter
@Setter

public class BookingPassengerDto {
    private Long id;
    private String name;
    private Integer age;
    private String gender;
    private String seatNumber;
    private String coachId;
}
