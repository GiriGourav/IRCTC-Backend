package com.substring.irctc.controllers;

import com.substring.irctc.dto.TrainDTO;
import com.substring.irctc.entity.ImageMetadata;
import com.substring.irctc.entity.Train;
import com.substring.irctc.service.FileUploadService;
import com.substring.irctc.service.TrainService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
//import org.springframework.web.ErrorResponse;

import com.substring.irctc.dto.ErrorResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
// Controller + ResponseBody = RestController
@RequestMapping("/trains")
public class TrainController {

    @Autowired
    private TrainService trainService;
@Autowired
    private FileUploadService fileUploadService;
    @RequestMapping("/photo")
    public ImageMetadata uploadTrainUpload(
              @RequestParam("file") MultipartFile file)
            throws IOException {
//Process the file
        ImageMetadata imageMetadata=fileUploadService.upload(file);
//        using Repository save this to database
return imageMetadata;
    }

//    get all
//    @RequestMapping(value = "/",method = RequestMethod.GET)
    @GetMapping
public Page<TrainDTO> all(
        @RequestParam(value = "page",defaultValue = "0") int page,
        @RequestParam(value = "size",defaultValue = "10") int size,
        @RequestParam(value = "sortBy",defaultValue = "name") String sortBy,
        @RequestParam(value = "sortDir",defaultValue = "asc") String sortDir


        ) throws InterruptedException {

    return this.trainService.all(page,size,sortBy,sortDir);
}
@DeleteMapping("/{trainNo}")
public void delete(@PathVariable String trainNo)
{

    this.trainService.delete(trainNo);

}

//get Single
//    train/1234
//    train/1235
//    @GetMapping("/{trainNo}/{trainName}")
//    public Train get(@PathVariable String tainNo, String trainName)
//    {
//        return this.trainService.get(tainNo);
//    }


    @GetMapping("/{trainNo}")
    public ResponseEntity<TrainDTO> get(@PathVariable String trainNo)
    {
        return new ResponseEntity<>(this.trainService.get(trainNo),HttpStatus.OK);
    }

//    add
    @PostMapping
    public ResponseEntity<TrainDTO> add(@Valid @RequestBody TrainDTO trainDTO)
    {

        return new ResponseEntity<>(this.trainService.add(trainDTO),HttpStatus.CREATED);
    }
//    @RequestMapping("/all")
//    @ResponseBody
//    public List<Train> listTrains()
//    {


//        System.out.println("All Trains are here");
//
//        Train train1=new Train();
//        train1.setTrainNo("1234");
//        train1.setName("LKO-DELHI SUPPER_FAST");
//        train1.setCoaches(10);
//
//        Train train2=new Train();
//        train2.setTrainNo("1235");
//        train2.setName("LKO-MUMBAI SUPPER_FAST");
//        train2.setCoaches(12);
//
//        List<Train> trainList=new ArrayList<>();
//        trainList.add(train1);
//        trainList.add(train2);
//        return trainList;
//    }
//
//    @RequestMapping("/get-one")
//    @ResponseBody
//    public Train getTrain()
//    {
//
//        Train train1=new Train();
//        train1.setTrainNo("1234");
//        train1.setName("LKO-DELHI SUPPER_FAST");
//        train1.setCoaches(10);
//        return train1;
//    }

//for exception handling
//    @ExceptionHandler(NoSuchElementException.class)
//    public ErrorResponse handleNoSuchException(NoSuchElementException exception)
//    {
//
//        ErrorResponse response= new ErrorResponse("Train not Found !! " + exception.getMessage(),"404",false);
//        return response;
//
//    }

}
