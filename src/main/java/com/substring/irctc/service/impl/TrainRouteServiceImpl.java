package com.substring.irctc.service.impl;

import com.substring.irctc.dto.TrainRouteDto;
import com.substring.irctc.entity.Station;
import com.substring.irctc.entity.Train;
import com.substring.irctc.entity.TrainRoute;
import com.substring.irctc.exceptions.ResourceNotFoundException;
import com.substring.irctc.repositories.StationRepo;
import com.substring.irctc.repositories.TrainRepository;
import com.substring.irctc.repositories.TrainRouteRepository;
import com.substring.irctc.service.TrainRouteService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TrainRouteServiceImpl implements TrainRouteService{
    private TrainRepository trainRepository;
    private StationRepo stationRepo;
    private ModelMapper modelMapper;
    private TrainRouteRepository trainRouteRepository;

    public TrainRouteServiceImpl(TrainRepository trainRepository, StationRepo stationRepo, ModelMapper modelMapper, TrainRouteRepository trainRouteRepository) {
        this.trainRepository = trainRepository;
        this.stationRepo = stationRepo;
        this.modelMapper = modelMapper;
        this.trainRouteRepository = trainRouteRepository;
    }


    @Override
    public TrainRouteDto addRoute(TrainRouteDto dto) {

        Train train=this.trainRepository.findById(dto.getTrain().getId()).orElseThrow(()-> new ResourceNotFoundException("Train not found with Id:"+dto.getTrain().getId()));
        Station station=this.stationRepo.findById(dto.getStation().getId()).orElseThrow(()->new ResourceNotFoundException("Station not found with Id:"+dto.getStation().getId()));

        TrainRoute trainRoute=modelMapper.map(dto, TrainRoute.class);
        trainRoute.setTrain(train);
        trainRoute.setStation(station);

        TrainRoute savedTrainRoute= trainRouteRepository.save(trainRoute);

        TrainRouteDto savedtrainRouteDto=modelMapper.map(savedTrainRoute,TrainRouteDto.class);
        return savedtrainRouteDto;
    }

    @Override
    public List<TrainRouteDto> getRoutesByTrain(Long trainId) {
        Train train=this.trainRepository.findById(trainId).orElseThrow(()-> new ResourceNotFoundException("Train not found with Id:"+trainId));

        List<TrainRoute> trainRoutes=this.trainRouteRepository.findByTrainId(trainId);
        List<TrainRouteDto> trainRouteDtos= trainRoutes.stream().map(trainRoute -> modelMapper.map(trainRoute,TrainRouteDto.class)).toList();

        return trainRouteDtos;
    }

    @Override
    public TrainRouteDto updateRoute(Long id, TrainRouteDto dto) {
        TrainRoute existingRoute=this.trainRouteRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Route not found with Id:"+id));


        Station station=this.stationRepo.findById(dto.getStation().getId()).orElseThrow(()->new ResourceNotFoundException("Station not found with id:"+dto.getStation().getId()));
        Train train=this.trainRepository.findById(dto.getTrain().getId()).orElseThrow(()->new ResourceNotFoundException("Train not found with the id"+dto.getTrain().getId()));
        existingRoute.setStation(station);
        existingRoute.setTrain(train);
        existingRoute.setStationOrder(dto.getStationOrder());
        existingRoute.setArrivalTime(dto.getArrivalTime());
        existingRoute.setDepartureTime(dto.getDepartureTime());
        existingRoute.setHaltTime(dto.getHaltMinutes());
        existingRoute.setDistanceFromSource(dto.getDistanceFromSource());

        TrainRoute updatedRoute=trainRouteRepository.save(existingRoute);
        TrainRouteDto updatedRouteDto=modelMapper.map(updatedRoute,TrainRouteDto.class);
        return updatedRouteDto;
    }

    @Override
    public void deleteRoute(Long id) {
        TrainRoute existingRoute=this.trainRouteRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Route not found with Id:"+id));
        trainRouteRepository.delete(existingRoute);
    }
}
