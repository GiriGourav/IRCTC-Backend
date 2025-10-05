package com.substring.irctc.service.impl;

import com.substring.irctc.dto.TrainScheduleDto;
import com.substring.irctc.entity.Train;
import com.substring.irctc.entity.TrainSchedule;
import com.substring.irctc.exceptions.ResourceNotFoundException;
import com.substring.irctc.repositories.TrainRepository;
import com.substring.irctc.repositories.TrainScheduleRepository;
import com.substring.irctc.service.TrainScheduleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TrainScheduleServiceImpl implements TrainScheduleService {

    private TrainScheduleRepository trainScheduleRepository;
    private TrainRepository trainRepository;
    private ModelMapper modelMapper;

    public TrainScheduleServiceImpl(TrainScheduleRepository trainScheduleRepository, TrainRepository trainRepository, ModelMapper modelMapper) {
        this.trainScheduleRepository = trainScheduleRepository;
        this.trainRepository = trainRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public TrainScheduleDto createTrainSchedule(TrainScheduleDto trainScheduleDto) {
        Train train=trainRepository.findById(trainScheduleDto.getTrainId()).orElseThrow(()->new ResourceNotFoundException("Train not found with given id:"+trainScheduleDto.getTrainId()));
        TrainSchedule trainSchedule=modelMapper.map(trainScheduleDto, TrainSchedule.class);
        trainSchedule.setTrain(train);
        TrainSchedule savedSchedule=trainScheduleRepository.save(trainSchedule);
        return modelMapper.map(savedSchedule,TrainScheduleDto.class);
    }

    @Override
    public List<TrainScheduleDto> getTrainScheduleByTrainId(Long trainId) {
        List<TrainSchedule> trainSchedules= trainScheduleRepository.findByTrainId(trainId);
        return trainSchedules.stream().map(trainSchedule ->modelMapper.map(trainSchedule,TrainScheduleDto.class)).toList();
    }

    @Override
    public void deleteTrainScheduleByTrainId(Long trainScheduleId) {
       TrainSchedule trainSchedule= trainScheduleRepository.findById(trainScheduleId).orElseThrow(()->new ResourceNotFoundException("Train schedule not found with the given id: "+trainScheduleId));
       trainScheduleRepository.delete(trainSchedule);
    }

    @Override
    public TrainScheduleDto updateTrainSchedule(Long trainScheduleId, TrainScheduleDto trainScheduleDto) {
        TrainSchedule trainSchedule= trainScheduleRepository.findById(trainScheduleId).orElseThrow(()->new ResourceNotFoundException("Train schedule not found with the given id: "+trainScheduleId));
        Train train=trainRepository.findById(trainScheduleDto.getTrainId()).orElseThrow(()->new ResourceNotFoundException("Train not found with the given id:"+trainScheduleDto.getTrainId()));
        trainSchedule.setTrain(train);
        trainSchedule.setAvailableSeats(trainScheduleDto.getAvailableSeats());
        trainSchedule.setRunDate(trainScheduleDto.getRunDate());
        TrainSchedule updatedSchedule = trainScheduleRepository.save(trainSchedule);
        return modelMapper.map(updatedSchedule,TrainScheduleDto.class);
    }
}
