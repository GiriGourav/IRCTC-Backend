package com.substring.irctc.controllers.admin;

import com.substring.irctc.dto.TrainDTO;
import com.substring.irctc.service.TrainService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("adminTrainController")
@RequestMapping("/admin/trains")
public class TrainController {

    private TrainService trainService;

    public TrainController(TrainService trainService) {
        this.trainService = trainService;
    }

    @Operation(summary = "Create a new train",
    description = "This API is used to create a new train")
    @PostMapping
    public ResponseEntity<TrainDTO> createTrain(
            @RequestBody TrainDTO trainDTO
            )
    {

//        System.out.println(trainDTO.getName());
//        System.out.println(trainDTO.getNumber());
//        System.out.println(trainDTO.getSourceStation().getId());
        return new ResponseEntity<>(trainService.createTrain(trainDTO),HttpStatus.CREATED);
    }

    @Operation(summary = "Get all trains",
    description = "This API is used to get all trains")
    @GetMapping
    public List<TrainDTO> getAllTrains(){

        return trainService.getAllTrains();
    }


    @Operation(summary = "Get a train by id",
    description = "This API is used to get a train by id")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200",description = "Train found"),
            @ApiResponse(responseCode = "404",description = "Train not found"),
            @ApiResponse(responseCode = "500",description = "Internal server error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TrainDTO> getTrainById(
            @Parameter(description = "Train id to get the details of train",required = true)
            @PathVariable("id") Long id){
        return new ResponseEntity<>(trainService.getTrainById(id),HttpStatus.OK);
    }


    @Operation(summary = "Update a train",
    description = "This API is used to update a train")
    @PutMapping("/{id}")
    public ResponseEntity<TrainDTO> updateTrain(
            @PathVariable("id") Long id,
            @RequestBody TrainDTO trainDTO
    ){
        return new ResponseEntity<>(trainService.updateTrainById(id,trainDTO),HttpStatus.OK);
    }


    @Operation(summary = "Delete a train",
    description = "This API is used to delete a train")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrain(
            @PathVariable("id") Long id
    ){
        trainService.deleteTrain(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
