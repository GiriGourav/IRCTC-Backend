package com.substring.irctc.controllers.admin;

import com.substring.irctc.dto.TrainSeatDto;
import com.substring.irctc.service.TrainSeatService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/train-seats")
public class TrainSeatController {
    private TrainSeatService trainSeatService;

    public TrainSeatController(TrainSeatService trainSeatService) {
        this.trainSeatService = trainSeatService;
    }
@Operation(summary = "Create a seat",
description = "This API is used to create a seat")
    @PostMapping
    public ResponseEntity<TrainSeatDto> createSeat(
            @RequestBody TrainSeatDto trainSeatDto){
        TrainSeatDto createdSeat=trainSeatService.crateSeatInfo(trainSeatDto);
        return ResponseEntity.status(201).body(createdSeat);
    }

//    get dibba of train schedule
    @Operation(summary = "Get seats by schedule id",
description = "This API is used to get seats by schedule id")
    @GetMapping("/schedule/{scheduleId}")
    public ResponseEntity<List<TrainSeatDto>> getSeatsByScheduleId(
            @PathVariable Long scheduleId
    ) {
       List<TrainSeatDto> seats= trainSeatService.getSeatInfoByTrainScheduleId(scheduleId);

       return ResponseEntity.ok(seats);
    }
@Operation(summary = "Delete a seat",
description = "This API is used to delete a seat")
    @DeleteMapping("/{seatId}")
    public ResponseEntity<Void> deleteSeat(
            @PathVariable Long seatId
    ){
        trainSeatService.deleteSeatInfo(seatId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Update a seat",
description = "This API is used to update a seat")
    @PutMapping("/{seatId}")
    public ResponseEntity<TrainSeatDto> updateSeat(
            @PathVariable Long seatId,
            @RequestBody TrainSeatDto trainSeatDto
    ){
        TrainSeatDto updatedSeat= trainSeatService.updateSeatInfo(seatId,trainSeatDto);
        return ResponseEntity.ok(updatedSeat);
    }
}
