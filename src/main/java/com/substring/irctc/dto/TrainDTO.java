package com.substring.irctc.dto;


import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrainDTO {


    private Long id;
    @NotEmpty(message = "train number is required !!")

    @Size(min = 3,max = 20,message = "invalid length")
    @Pattern(regexp = "^\\d+$", message = "Invalid train number, It contain only digits")
    @Id
    private String number;

    @Pattern(regexp = "^[A-Za-z][A-Za-z -]*[A-Za-z]$", message = "Invalid train name ")
    private String name;

    private Integer totalDistance;

    private StationDto sourceStation;

    private StationDto destinationStation;


}
