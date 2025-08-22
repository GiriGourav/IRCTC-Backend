package com.substring.irctc.controllers.admin;

import com.substring.irctc.dto.TrainRouteDto;
import com.substring.irctc.repositories.TrainRouteRepository;
import com.substring.irctc.service.TrainRouteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/train-routes")
public class TrainRouteController {

    private TrainRouteService trainRouteService;
    public TrainRouteController(TrainRouteService trainRouteService){
        this.trainRouteService=trainRouteService;
    }

    @PostMapping
    public ResponseEntity<TrainRouteDto> createTrainRoute(
            @RequestBody TrainRouteDto trainRouteDto){
        TrainRouteDto createdRoute=trainRouteService.addRoute(trainRouteDto);
        return ResponseEntity.status(201).body(createdRoute);
    }

    @GetMapping("/train/{trainId}")
    public ResponseEntity<List<TrainRouteDto>> getRoutteByTrain(@PathVariable Long trainId){
        List<TrainRouteDto> routes =trainRouteService.getRoutesByTrain(trainId);
        return ResponseEntity.ok(routes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrainRouteDto> updateTrainRoute(
            @PathVariable Long id,
            @RequestBody TrainRouteDto trainRouteDto){
        TrainRouteDto updatedRoute=trainRouteService.updateRoute(id,trainRouteDto);
        return ResponseEntity.ok(updatedRoute);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>deleteTrainRoute(
            @PathVariable Long id)
           {
        trainRouteService.deleteRoute(id);
        return ResponseEntity.noContent().build();
    }
}
