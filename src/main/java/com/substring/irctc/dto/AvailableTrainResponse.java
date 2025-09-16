package com.substring.irctc.dto;

import com.substring.irctc.entity.CoachType;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AvailableTrainResponse {

    private Long trainId;;
    private String trainName;
    private LocalDateTime arrivalTime;
    private LocalDateTime departureTime;
    private Map<CoachType, Integer> seatsAvailable;;
    private Map<CoachType, Double> priceByCoach;
}
