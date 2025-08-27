package com.substring.irctc.entity;

import jakarta.persistence.*;
import lombok.*;



@Entity
@Table(name = "train_seats")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
//Dibba
public class TrainSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "train_schedule_id")
    private TrainSchedule trainSchedule;

    @Enumerated(EnumType.STRING)
    private CoachType coachType;

    private Integer totalSeats;
//    42
    private Integer availableSeats;

    private double price;

    private Integer trainSeatOrder;
//1
    private int seatNumberToAssign;

    public boolean isCoachFull()
    {
        return availableSeats<=0;
    }

    public boolean isSeatAvailable(int seatToBeBooked)
    {
        return seatToBeBooked<=availableSeats;
    }


}
