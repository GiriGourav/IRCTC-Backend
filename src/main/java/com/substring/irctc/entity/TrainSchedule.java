package com.substring.irctc.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "train_schedule")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrainSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate runDate;

    @ManyToOne
    @JoinColumn(name = "train_id")
    private Train train;

    private Integer availableSeats;

//    Kitne seats ki types
    @OneToMany(mappedBy = "trainSchedule")
    private List<TrainSeat> trainSeats;


//    booking
    @OneToMany(mappedBy = "trainSchedule")
    private List<Booking> bookings;
}
