package com.substring.irctc.controllers.admin;

import com.substring.irctc.dto.TrainSeatDto;
import com.substring.irctc.service.TrainSeatService;
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

    @PostMapping
    public ResponseEntity<TrainSeatDto> createSeat(
            @RequestBody TrainSeatDto trainSeatDto){
        TrainSeatDto createdSeat=trainSeatService.crateSeatInfo(trainSeatDto);
        return ResponseEntity.status(201).body(createdSeat);
    }

//    get dibba of train schedule
    @GetMapping("/schedule/{scheduleId}")
    public ResponseEntity<List<TrainSeatDto>> getSeatsByScheduleId(
            @PathVariable Long scheduleId
    ) {
       List<TrainSeatDto> seats= trainSeatService.getSeatInfoByTrainScheduleId(scheduleId);

       return ResponseEntity.ok(seats);
    }

    @DeleteMapping("/{seatId}")
    public ResponseEntity<Void> deleteSeat(
            @PathVariable Long seatId
    ){
        trainSeatService.deleteSeatInfo(seatId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{seatId}")
    public ResponseEntity<TrainSeatDto> updateSeat(
            @PathVariable Long seatId,
            @RequestBody TrainSeatDto trainSeatDto
    ){
        TrainSeatDto updatedSeat= trainSeatService.updateSeatInfo(seatId,trainSeatDto);
        return ResponseEntity.ok(updatedSeat);
    }
}
