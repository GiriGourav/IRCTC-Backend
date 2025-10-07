package com.substring.irctc.service;

import com.substring.irctc.dto.AvailableTrainResponse;
import com.substring.irctc.dto.TrainDTO;
import com.substring.irctc.dto.UserTrainSearchRequest;

import java.time.LocalDate;
import java.util.List;

public interface TrainService {

//    Create Train
    public TrainDTO createTrain(TrainDTO trainDTO);

//Get all trains
    public List<TrainDTO> getAllTrains();

//    get train by train id
    public TrainDTO getTrainById(Long id);

    //update train
    public TrainDTO updateTrain(Long id, TrainDTO trainDTO);

//    Delete Train
    public void deleteTrain(Long id);

//    search train for booking

    public List<AvailableTrainResponse> userTrainSearch(UserTrainSearchRequest request);
}
