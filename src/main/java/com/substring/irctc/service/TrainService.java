package com.substring.irctc.service;

import com.substring.irctc.dto.TrainDTO;

import java.util.List;

public interface TrainService {

//    Create Train
    public TrainDTO createTrain(TrainDTO trainDTO);

//Get all trains
    public List<TrainDTO> getAllTrains();

//    get train by train id
    public TrainDTO getTrainById(Long id);

//    update train
    public TrainDTO updateTrainById(Long id, TrainDTO trainDTO);

//    Delete Train
    public void deleteTrain(Long id);
}
