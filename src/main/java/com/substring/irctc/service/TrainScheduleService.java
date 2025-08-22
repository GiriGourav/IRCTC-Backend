package com.substring.irctc.service;

import com.substring.irctc.dto.TrainScheduleDto;
import com.substring.irctc.entity.TrainSchedule;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TrainScheduleService {

    TrainScheduleDto createTrainSchedule(TrainScheduleDto trainScheduleDto);
    List<TrainScheduleDto> getTrainScheduleByTrainId(Long trainId);
    void deleteTrainScheduleByTrainId(Long trainScheduleId);

    TrainScheduleDto updateTrainSchedule(Long trainScheduleId, TrainScheduleDto trainScheduleDto);
}
