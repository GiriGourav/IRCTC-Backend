package com.substring.irctc.controllers.admin;

import com.substring.irctc.dto.TrainDTO;
import com.substring.irctc.service.TrainService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("adminTrainController")
@RequestMapping("/admin/trains")
public class TrainController {


    @PostMapping
    public ResponseEntity<TrainDTO> createTrain(
            @RequestBody TrainDTO trainDTO
            )
    {
        System.out.println(trainDTO.getName());
        System.out.println(trainDTO.getNumber());
        System.out.println(trainDTO.getSourceStation().getId());
        return new ResponseEntity<>(trainDTO,HttpStatus.CREATED);
    }

}
