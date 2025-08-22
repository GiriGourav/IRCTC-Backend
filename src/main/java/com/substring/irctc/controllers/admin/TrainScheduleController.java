package com.substring.irctc.controllers.admin;

import com.substring.irctc.dto.TrainScheduleDto;
import com.substring.irctc.service.TrainScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/train-schedules")
public class TrainScheduleController {

    private TrainScheduleService trainScheduleService;

    public TrainScheduleController(TrainScheduleService trainScheduleService) {
        this.trainScheduleService = trainScheduleService;
    }
//Creating train Schedule
    @PostMapping
    public ResponseEntity<TrainScheduleDto> createTrainSchedule(
            @RequestBody TrainScheduleDto trainScheduleDto){

        TrainScheduleDto createdSchedule= trainScheduleService.createTrainSchedule(trainScheduleDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSchedule);

    }
//    get train Schedule by train id

    @GetMapping("/train/{trainId}")
    public List<TrainScheduleDto> getTrainScheduleByTrainId(
            @PathVariable Long trainId
    ){
        return trainScheduleService.getTrainScheduleByTrainId(trainId);
    }

//    delete train schedule
    @DeleteMapping("/train/{trainScheduleId}")
    public ResponseEntity<Void> deleteTrainSchedule(
            @PathVariable Long trainScheduleId
            )    {
          trainScheduleService.deleteTrainScheduleByTrainId(trainScheduleId);
          return ResponseEntity.noContent().build();
    }

//    update train schedule
    @PutMapping("/{trainScheduleId}")
    public ResponseEntity<TrainScheduleDto> updateTrainSchedule(@PathVariable Long trainScheduleId
    ,@RequestBody TrainScheduleDto trainScheduleDto)
    {
        TrainScheduleDto updatedSchedule=trainScheduleService.updateTrainSchedule(trainScheduleId,trainScheduleDto);
        return ResponseEntity.ok(updatedSchedule);
    }
}
