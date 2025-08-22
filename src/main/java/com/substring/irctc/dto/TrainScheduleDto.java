package com.substring.irctc.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrainScheduleDto {
    private Long  Id;
    private Long trainId;
    private LocalDate runDate;
    private Integer availableTime;
}
