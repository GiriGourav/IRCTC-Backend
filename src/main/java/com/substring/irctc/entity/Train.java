package com.substring.irctc.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.substring.irctc.annotations.ValidCoach;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name="trains")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Train {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String number;

    private String name;

   private Integer totalDistance;

   @ManyToOne
   @JoinColumn(name = "source_station_id")
   private Station sourceStation;

   @ManyToOne
   @JoinColumn(name = "destination_station_id")
   private Station destinationStation;

//train routs
//    schedule


   @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
   private TrainImage trainImage;



}
