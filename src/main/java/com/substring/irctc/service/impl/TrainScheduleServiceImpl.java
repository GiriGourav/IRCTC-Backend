package com.substring.irctc.service.impl;

import com.substring.irctc.dto.TrainScheduleDto;
import com.substring.irctc.repositories.TrainRepository;
import com.substring.irctc.repositories.TrainScheduleRepository;
import com.substring.irctc.service.TrainScheduleService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class TrainScheduleServiceImpl implements TrainScheduleService {

    private TrainScheduleRepository trainScheduleRepository;
    private TrainRepository trainRepository;
    private ModelMapper modelMapper;


    @Override
    public TrainScheduleDto createTrainSchedule(TrainScheduleDto trainScheduleDto) {
        return null;
    }

    @Override
    public List<TrainScheduleDto> getTrainScheduleByTrainId(Long trainId) {
        return List.of();
    }

    @Override
    public void deleteTrainScheduleByTrainId(Long trainScheduleId) {

    }

    @Override
    public TrainScheduleDto updateTrainSchedule(Long trainScheduleId, TrainScheduleDto trainScheduleDto) {
        return null;
    }
}
